package daniilk.example.persistance;

import lombok.Data;

//ToDo comment out lines
//@Entity
@Data
public class Contact {
    //ToDo comment out lines

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    public Contact(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Contact() {

    }
}
