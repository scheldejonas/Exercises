package domain;

/**
 * Created by scheldejonas on 06/02/17.
 */
public class Turnstile {

    private Long id;
    private String name;

    public Turnstile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
