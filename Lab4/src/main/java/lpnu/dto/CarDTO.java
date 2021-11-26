package lpnu.dto;

public class CarDTO {
    private Long id;
    private String type;
    private String name;
    private int year;

    public CarDTO() {
    }

    public CarDTO(final Long id, final String type, final String name, final int year) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}