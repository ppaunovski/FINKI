#include <stdio.h>

#include <stdlib.h>

#include <sys/wait.h>

#include <sys/types.h>

int main(int argc, char *argv[])

{

    pid_t pid;

    if ((pid = fork()) == 0)

    { /* child */

        int num;

        do{
            printf("Vnesete broj: ");
            scanf("%d", &num);
            printf("Vnesovte %d.\n", num);
        } while (num != 0);

        printf("sleeping...\n");

        sleep(5);

        printf("waking ... and exiting\n");

    } else {

        if (pid > 0)

        {

            printf("waiting for child\n");

            wait(NULL);

            printf("child woke up\n");

        }

    }

}

