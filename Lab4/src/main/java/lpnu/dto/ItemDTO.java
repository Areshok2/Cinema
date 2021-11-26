package lpnu.dto;

public class ItemDTO {
    private Long id;
    private String name;
    private double price;

    public ItemDTO() {
    }

    public ItemDTO(final Long id, final String name, final double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(final String title) {
        this.name = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}