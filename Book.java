package LibrarySystem;
public class Book {
	String Title;
	String VolumeNumber;
	String Author;
	String Genre;
	String ShelfNumber;
	
	public Book() {
		Title = null;
		VolumeNumber = null;
		Author = null;
		Genre = null;
		ShelfNumber = null;
	}
	
	public Book(String Title, String VolumeNumber, String Author, String Genre, String ShelfNumber) {
		this.Title = Title;
		this.VolumeNumber = VolumeNumber;
		this.Author = Author;
		this.Genre = Genre;
		this.ShelfNumber = ShelfNumber;
	}
	public Book(String[] list) {
		this.Title = list[0];
		this.VolumeNumber = list[1];
		this.Author = list[2];
		this.Genre = list[3];
		this.ShelfNumber = list[4];
	}
	
	public String getTitle(){
		return Title;
	}
	public String getVolumeNumber() {
		return VolumeNumber;
	}
	public String getAuthor(){
		return Author;
	}
	public String getGenre(){
		return Genre;
	}
	public String getShelfNumber() {
		return ShelfNumber;
	}
	
	public void setTitle(String Title){
		this.Title = Title;
	}
	public void setVolumeNumber(String VolumeNumber) {
		this.VolumeNumber = VolumeNumber;
	}
	public void setAuthor(String Author){
		this.Author = Author;
	}
	public void setGenre(String Genre){
		this.Genre = Genre;
	}
	public void setShelfNumber(String ShelfNumber) {
		this.ShelfNumber = ShelfNumber;
	}
	
	public String toString() {
		return String.format("%s vol %s" + " by %s" + "\nGenre: %s"
				+ "\nShelf Number: %s", getTitle(), getVolumeNumber(), getAuthor(), getGenre(), getShelfNumber());
	}
	public String textFormat() {
		return String.format("%s/%s/%s/%s/%s", getTitle(), getVolumeNumber(), getAuthor(), getGenre(), getShelfNumber());
	}
}
