package LIS.Model.VO;

import java.util.List;

public class BookSearchForm {
    private List<SearchFormPart> searchFormParts;
    private String storageLocation;
    private String publicationYear1;
    private String publicationYear2;

    public List<SearchFormPart> getSearchFormParts() { return searchFormParts; }
    public void setSearchFormParts(List<SearchFormPart> searchFormParts) { this.searchFormParts = searchFormParts; }
    public String getStorageLocation() { return storageLocation; }
    public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }
    public String getPublicationYear1() { return publicationYear1; }
    public void setPublicationYear1(String publicationYear1) { this.publicationYear1 = publicationYear1; }
    public String getPublicationYear2() { return publicationYear2; }
    public void setPublicationYear2(String publicationYear2) { this.publicationYear2 = publicationYear2; }

    @Override
    public String toString() {
        return searchFormParts+" storageLocation: "+storageLocation+" || publicationYear: "+publicationYear1+" ~ "+publicationYear2+"\n";
    }
}
