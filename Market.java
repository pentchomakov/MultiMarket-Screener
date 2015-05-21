import java.util.ArrayList;


public class Market {
	private String marketName;
	private String marketLocation;
	private int size;
	private int openingTime;
	private int closingTime;
	private ArrayList<Stock> stocks;  
	
	public Market(String marketName, String marketLocation, int size, int openingTime, int closingTime) {
		this.marketName = marketName;
		this.marketLocation = marketLocation;
		this.size = size;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.stocks = new ArrayList<Stock>();
	}
	
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	public String getMarketName() {
		return this.marketName;
	}
	
	public void setMarketLocation(String marketLocation) {
		this.marketLocation = marketLocation;
	}
	
	public String getMarketLocation() {
		return this.marketLocation;
	}
	
	public void setMarketSize(int size) {
		this.size = size;
	}
	
	public int getMarketSize() {
		return this.size;
	}
	
	public void addStock(Stock stock) {
		this.stocks.add(stock);
	}
	
	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}
	
	public int getOpeningTime() {
		return this.openingTime;
	}
	
	public void setClosingTime(int openingTime) {
		this.closingTime = openingTime;
	}
	
	public int getClosingTime() {
		return this.closingTime;
	}
	public ArrayList<Stock> getStockList() {
		return this.stocks;
	}
	
}
