package org.wsbn.controllers.reports;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.wsbn.data_services.SchoolService;
import org.wsbn.dto.YearDto;
import org.wsbn.vo.Player;
import org.wsbn.vo.Sale;

 
@ManagedBean(name="dtGroupView")
@ViewScoped
public class GroupViewController implements Serializable {
     
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	
	// COLLABORATORS
	// IoC
	@ManagedProperty(value = "#{schoolService}")
	private SchoolService		schoolService;

	// IoC
	// must provide the setter method
	
	public void setSchoolService(SchoolService pSchoolService)
	{

		this.schoolService = pSchoolService;
	}
	
	
	private final static String[] manufacturers;
    private List<Sale> sales;
     
    private final static String[] playerNames;
    private List<YearDto> yearsDtoList;
    private List<Integer> years;
    private List<Player> players;
     
    static {        
        manufacturers = new String[10];
        manufacturers[0] = "Apple";
        manufacturers[1] = "Samsung";
        manufacturers[2] = "Microsoft";
        manufacturers[3] = "Philips";
        manufacturers[4] = "Sony";
        manufacturers[5] = "LG";
        manufacturers[6] = "Sharp";
        manufacturers[7] = "Panasonic";
        manufacturers[8] = "HTC";
        manufacturers[9] = "Nokia";
    }
         
    static {
        playerNames = new String[10];
        playerNames[0] = "Lionel Messi";
        playerNames[1] = "Cristiano Ronaldo";
        playerNames[2] = "Arjen Robben";
        playerNames[3] = "Franck Ribery";
        playerNames[4] = "Ronaldinho";
        playerNames[5] = "Luis Suarez";
        playerNames[6] = "Sergio Aguero";
        playerNames[7] = "Zlatan Ibrahimovic";
        playerNames[8] = "Neymar Jr";
        playerNames[9] = "Andres Iniesta";
    }
         
    @PostConstruct
    public void init() {
        sales = new ArrayList<Sale>();
        for(int i = 0; i < 10; i++) {
            sales.add(new Sale(manufacturers[i], getRandomAmount(), getRandomAmount(), getRandomPercentage(), getRandomPercentage()));
        }
         
        yearsDtoList = this.schoolService.getYearsList();
        years = this.buildYearsList(yearsDtoList);
         
         
        players = new ArrayList<Player>();
        for(int i = 0; i < 10; i++) {
            players.add(new Player(playerNames[i], generateRandomGoalStatsData()));
        }
    }
    
    private List<Integer> buildYearsList(List<YearDto> pDtoList)
    {
    	List<Integer> oResponse = new ArrayList<Integer>();
    	
    	for(YearDto oDto : pDtoList)
    	{
    		oResponse.add(oDto.getYear());
    	}
    	
    	
    	return oResponse;
    }
 
    public List<Sale> getSales() {
        return sales;
    }
 
    private int getRandomAmount() {
        return (int) (Math.random() * 100000);
    }
 
    private int getRandomPercentage() {
        return (int) (Math.random() * 100);
    }
     
    public String getLastYearTotal() {
        int total = 0;
 
        for(Sale sale : getSales()) {
            total += sale.getLastYearSale();
        }
 
        return new DecimalFormat("###,###.###").format(total);
    }
 
    public String getThisYearTotal() {
        int total = 0;
 
        for(Sale sale : getSales()) {
            total += sale.getThisYearSale();
        }
 
        return new DecimalFormat("###,###.###").format(total);
    }
 
    public List<Integer> getYears() {
        return years;
    }
     
    public int getYearCount() {
        return years.size();
    }
 
    public List<Player> getPlayers() {
        return players;
    }
 
    private Map<Integer,Integer> generateRandomGoalStatsData() {
        Map<Integer,Integer> stats = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < 8; i++) 
        {
            stats.put(years.get(i), getRandomGoals());
            
        }
         
        return stats;
    }
     
    private int getRandomGoals() {
        return (int) (Math.random() * 50);
    }
}