
public class SearchResult {

    private String header;
    private String link;

    public SearchResult(String header, String link) {
        this.header = header;
        this.link = link;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "header='" + header + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
