package lab8.XML;

import org.w3c.dom.Attr;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface XMLComponent{
    void addAttribute(String attribute, String value);
    //abstract void addComponent();
    String print();
    String getTag();
    List<Attribute> getAttributes();
    List<XMLComponent> getComponents();
    String getType();
}

class Attribute{
    String attribute;
    String value;

    Attribute(String attribute, String value){
        this.attribute = attribute;
        this.value = value;
    }
}

class XMLLeaf implements XMLComponent{
    List<Attribute> attributes;
    String data;
    String tag;


    public XMLLeaf(String tag, String data) {
        this.attributes = new ArrayList<>();
        this.data = data;
        this.tag = tag;
    }

    @Override
    public void addAttribute(String attribute, String value) {
        attributes.add(new Attribute(attribute, value));
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(openTag());
        sb.append(data);
        sb.append(closeTag());
        return sb.toString();
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public List<XMLComponent> getComponents() {
        return null;
    }

    @Override
    public String getType() {
        return "leaf";
    }

    private String openTag() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        attributes.stream()
                .forEach(attribute -> sb.append(" ").append(attribute.attribute).append("=\"").append(attribute.value).append("\""));
        sb.append(">");
        return sb.toString();
    }

    private String closeTag() {
        return "</" + tag + ">";
    }

    @Override
    public String toString() {
        return "XMLLeaf{" +
                "valuesByAttribute=" + attributes +
                ", data='" + data + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}

class XMLComposite implements XMLComponent{
    List<XMLComponent> components;
    List<Attribute> attributes;
    String tag;

    public XMLComposite(String tag) {
        this.components = new ArrayList<>();
        attributes = new ArrayList<>();
        this.tag = tag;
    }

    void addComponent(XMLComponent xmlComponent){

        components.add(xmlComponent);
    }
    @Override
    public void addAttribute(String attribute, String value) {
        attributes.add(new Attribute(attribute, value));
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public List<XMLComponent> getComponents() {
        return components;
    }

    @Override
    public String getType() {
        return "composite";
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        int indent = 0;
        sb.append(printComposite(this, indent));
        return sb.toString();
    }

    private String printComposite(XMLComponent xmlComponent, int indent) {
        StringBuilder sb = new StringBuilder();
        if(xmlComponent.getType().equals("leaf")){
            for(int i=0; i<indent; i++){
                sb.append("\t");
            }
            sb.append(xmlComponent.print()).append("\n");
            return sb.toString();

        }

            sb.append(openTag(xmlComponent.getTag(), xmlComponent.getAttributes(), indent)).append("\n");
            for(int i=0; i<xmlComponent.getComponents().size(); i++){
                sb.append(printComposite(xmlComponent.getComponents().get(i), indent+1));
            }
            sb.append(closeTag(xmlComponent.getTag(), xmlComponent.getAttributes(), indent)).append("\n");
            return sb.toString();



    }

    private String closeTag(String tag, List<Attribute> attributes, int indent) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<indent; i++){
            sb.append("\t");
        }
        sb.append("</");
        sb.append(tag);
        sb.append(">");
        return sb.toString();
    }

    private String openTag(String tag, List<Attribute> attributes, int indent) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<indent; i++){
            sb.append("\t");
        }
        sb.append("<").append(tag);
        attributes.stream()
                .forEach(attribute -> sb.append(" ").append(attribute.attribute).append("=\"").append(attribute.value).append("\""));
        sb.append(">");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "XMLComposite{" +
                "components=" + components +
                ", valuesByAttribute=" + attributes +
                ", tag='" + tag + '\'' +
                '}';
    }
}


public class XMLTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        XMLComponent component = new XMLLeaf("student", "Trajce Trajkovski");
        component.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        XMLComposite composite = new XMLComposite("name");
        composite.addComponent(new XMLLeaf("first-name", "trajce"));
        composite.addComponent(new XMLLeaf("last-name", "trajkovski"));
        composite.addAttribute("type", "redoven");
        component.addAttribute("program", "KNI");

        if (testCase==1) {
            //TODO Print the component object
            System.out.println(component.print());
        } else if(testCase==2) {
            //TODO print the composite object
            System.out.println(composite.print());
        } else if (testCase==3) {
            XMLComposite main = new XMLComposite("level1");
            main.addAttribute("level","1");
            XMLComposite lvl2 = new XMLComposite("level2");
            lvl2.addAttribute("level","2");
            XMLComposite lvl3 = new XMLComposite("level3");
            lvl3.addAttribute("level","3");
            lvl3.addComponent(component);
            lvl2.addComponent(lvl3);
            lvl2.addComponent(composite);
            lvl2.addComponent(new XMLLeaf("something", "blabla"));
            main.addComponent(lvl2);
            main.addComponent(new XMLLeaf("course", "napredno programiranje"));

            //TODO print the main object
            System.out.println(main.print());
        }
    }
}
