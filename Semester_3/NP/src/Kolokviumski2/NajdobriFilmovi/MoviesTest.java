package Kolokviumski2.NajdobriFilmovi;

import java.util.*;
import java.util.stream.Collectors;

class Movie{
    String title;
    int[] ratings;

    public Movie(String title, int[] ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    public String getTitle() {
        return title;
    }

    double averageRating(){
        return Arrays.stream(ratings).asDoubleStream().average().orElse(0);
    }

    double ratingCoefficient(){
        //просечен ретјтинг на филмот x вкупно број на рејтинзи на филмот / максимален број на рејтинзи (од сите филмови во листата)
        return averageRating() * ratings.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append(" (").append(String.format("%.2f", averageRating())).append(") ").append("of ").append(ratings.length).append(" ratings");
        return sb.toString();
    }
}

class MoviesList{
    List<Movie> movieList;

    public MoviesList() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(String title, int[] ratings){
        Movie movie = new Movie(title, ratings);
        movieList.add(movie);
    }

    public List<Movie> top10ByAvgRating(){
        return movieList.stream()
                .sorted(Comparator.comparing(Movie::averageRating).reversed().thenComparing(Movie::getTitle))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Movie> top10ByRatingCoef(){
        return movieList.stream()
                .sorted(Comparator.comparing((Movie movie) -> movie.ratingCoefficient()/maxRatings()).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    private int maxRatings(){
        return movieList.stream()
                .mapToInt(movie -> movie.ratings.length)
                .max().orElse(0);

    }
}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
