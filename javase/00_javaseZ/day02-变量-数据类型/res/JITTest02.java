/**
*使用字符串的concat实现5W次拼接耗时:1953毫秒！
*/
public class JITTest02{
	public static void main(String[] args){
		String s = "aaa" ;
		//性能监控  执行该循环拼接5W次 记录一下时间点， 执行完成后记录时间点  毫秒
		long begin = System.currentTimeMillis();
		for(int i=0;i<50000;i++){
			s = s.concat(i+"");
		}
		long end = System.currentTimeMillis();
		System.out.println("使用字符串的concat实现5W次拼接耗时:"+(end-begin)+"毫秒！");
	}	
}