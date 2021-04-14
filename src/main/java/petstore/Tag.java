package petstore;

import java.util.Objects;

public class Tag {
    private String id;
    private String name;

    public Tag(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "petstore.Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;

        return Objects.equals(this.id, tag.id) &&
                Objects.equals(this.name, tag.name);
    }

}
