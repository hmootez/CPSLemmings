package Lemmings.services;

public interface ILevel {
	
	//Types: bool, int, enum Nature {EMPTY, DIRT, METAL}
	
	/*Observateurs*/
	public int height();
	public int width();
	public boolean editing();
	public Nature nature(int x,int y);
	public int getXEntrance();
	public int getYEntrance();
	public int getXExit();
	public int getYExit();
	
	/*Constructeurs*/
	/**pre : x>0 & y>0
	 * post : height(init(x,y))=y
	 * post : width(init(x,y))=x
	 * post : editing(init(x,y))=true
	 * TODO Entrance/Exit values !!
	 * post : getXEntrance(init(x,y)) = 5
	 * post : getYEntrance(init(x,y)) = 5
	 * post : getXExit(init(x,y)) = 10
	 * post : getYExit(init(x,y)) = 10
	 * post : nature(init(h,w),x,y) => Nature.METAL FORALL (x,y) / (x=1 & 0<y<height()) || (y=1 & 0<x<width()) 
	//\||(x=width() & 0<y<height() || (y=height() & 0<x<width()) & nature(x,y) == Nature.METAL
	 * */
	public void init(int x, int y);
	
	/*Operateurs*/
	/**pre : x>0 & x<=width() & y>0 & y<height()
	 * post : nature(setNature(x,y,n),x,y)==n  
	 */
	public void setNature(int x,int y,Nature n);
	
	
	/**pre : FORALL (x,y) / (x=1 & 0<y<height()) || (y=1 & 0<x<width()) 
	//\||(x=width() & 0<y<height() || (y=height() & 0<x<width()) & nature(x,y) == Nature.METAL
	 * post : editing(goPlay())== false
	 * */
	public void goPlay();
	
	/**Pre : nature(x,y) == Nature.DIRT
	 * Pre : editing() == false
	 * post : nature(remove(x,y),x,y)==Nature.EMPTY
	 * 
	 * */
	public void remove(int x,int y);
	
	/**Pre : nature(x,y) == Nature.EMPTY
	 * Pre : editing() == false
	 * post : nature(remove(x,y),x,y)==Nature.DIRT
	 * */
	public void build(int x,int y);
	
	/**Observations*/


}
