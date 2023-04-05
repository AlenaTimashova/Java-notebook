package model;

public class Note {
    private Long id;
    private String header;
    private String text;

    public Note(String header, String text) {
        this.header = header;
        this.text = text;
        
    }

    public Note(Long id, String header, String text) {
        this(header, text);
        this.id = id;
        
        
    }
    public Long getId() {
        return id;
    }
    public String getHeader() {
        return header;
    }
    public String getText() {
        return text;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "Notebook [id=" + id + ", header=" + header + ", text=" + text + "]";
    }

}
