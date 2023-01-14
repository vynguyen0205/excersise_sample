package sample1;

class Movie {
    String title;
    String genre;
    double rating;
    int duration;

    Movie(String t, String g, double r, int d) {
        this.title = t;
        this.genre = g;
        this.rating = r;
        this.duration = d;
    }
}

public class MovieOrdering {
    Movie[] movies = new Movie[10];

    // Complexity: O(n)
    int max = 0;
    int length = 0;

    public void addMovie(Movie m) {
        movies[length] = m;
        length++;
    }

    int calcJoyfulness(Movie[] movies, int n) {
        if (n == 0) return 0;

        int joyfulness = movies[0].duration;

        Movie last = movies[0];
        for (int i = 1; i < n; i++) {
            Movie current = movies[i];
            if (!current.genre.equals(last.genre) && current.rating > last.rating) {
                joyfulness += current.duration;
            }
            last = current;
        }

        return joyfulness;
    }

    // Complexity: O(n)
    public int currentJoyfulness() {
        return calcJoyfulness(movies, length);
    }

    // Complexity: O(N!)
    public int maxJoyfulness() {
        Movie[] tmp = movies.clone();
        max = 0;

        boolean[] taken = new boolean[length];
        Movie[] current = new Movie[length];

        permute(taken, current, 0);

        movies = tmp;
        return max;
    }

    void permute(boolean[] taken, Movie[] current, int idx) {
        if (idx == length) {
            process(current);
            return;
        }

        for (int i = 0; i < length; i++) {
            if (taken[i]) continue;

            current[idx] = movies[i];
            permute(taken, current, idx + 1);
            taken[i] = false;
        }
    }

    void process(Movie[] solution) {
        int joyfulness = calcJoyfulness(solution, solution.length);
        if (joyfulness > max) {
            max = joyfulness;
        }
    }

    public static void main(String[] args) {
        Movie a = new Movie("Squid Game", "Thriller", 7.6, 120);
        Movie b = new Movie("Spider-Man", "Action", 8.5, 110);
        Movie c = new Movie("The Matrix Resurrections", "Action", 6.2, 140);
        MovieOrdering mo = new MovieOrdering();
        mo.addMovie(a);
        mo.addMovie(b);
        mo.addMovie(c);
        System.out.println("Current joyfulness: " + mo.currentJoyfulness()); // return 230
        System.out.println("Max joyfulness: " + mo.maxJoyfulness()); // return 370
    }
}
