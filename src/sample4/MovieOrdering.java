package sample4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Movie {
    String title;
    String genre;
    double rating;
    int duration;

    public Movie(String title, String genre, double rating, int duration) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
    }
}

public class MovieOrdering {

    List<Movie> movies = new ArrayList<>();
    int size = 0;

    // Complexity: O(1)
    public void addMovie(Movie m) {
        movies.add(m);
        size++;
    }

    // Complexity: O(n)
    public int currentJoyfulness() {
        return calJoyfulness(movies);
    }

    // Complexity: O()
    public int maxJoyfulness() {
        maxJoy = 0;
        Movie[] current = new Movie[size];
        boolean[] taken = new boolean[size];
        permute(movies, taken, current, 0);
        return maxJoy;
    }

    private void permute(List<Movie> input, boolean[] taken, Movie[] current, int idx) {
        if (idx == size) {
            process(current);
            return;
        }

        for (int i = 0; i < size; i++) {
            if (taken[i]) {
                continue;
            }

            current[idx] = input.get(i);
            taken[i] = true;
            permute(input, taken, current, idx + 1);
            taken[i] = false;
        }
    }

    int maxJoy = 0;
    private void process(Movie[] current) {
        int joy = calJoyfulness(Arrays.asList(current));
        if (joy > maxJoy) {
            maxJoy = joy;
        }
    }

    private int calJoyfulness(List<Movie> movies) {
        if (movies == null || size == 0) return 0;

        Movie last = movies.get(0);
        int total = last.duration;

        for (int i = 1; i < size; i++) {
            Movie curr = movies.get(i);
            if (!curr.genre.equals(last.genre) && curr.rating > last.rating) {
                total += curr.duration;
            }
            last = curr;
        }
        return total;
    }

    public static void main(String[] args) {
        Movie a = new Movie("Squid Game", "Thriller", 7.6, 120);
        Movie b = new Movie("Spider-Man", "Action", 8.5, 110);
        Movie c = new Movie("The Matrix Resurrections", "Action", 6.2, 140);
        MovieOrdering mo = new MovieOrdering();
        mo.addMovie(a);
        mo.addMovie(b);
        mo.addMovie(c);
        System.out.println("Current Joyfulness: " + mo.currentJoyfulness()); // return 230
        System.out.println("Max Joyfulness: " + mo.maxJoyfulness()); // return 370
    }
}
