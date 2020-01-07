package com.randomsturvs.collaboux;

import com.google.gson.internal.LinkedHashTreeMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//@ComponentScan(basePackages = "com.randomsturvs.collaboux.*")
//@RunWith(SpringRunner.class)
//@WebMvcTest(SecuredController.class)
@RunWith(MockitoJUnitRunner.class)
public class CollabouxApplicationTests {

	@Test
	public void stringToListTest(){
		String[] a = new String[]{"Maison Amrani"};
		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(a));
		arrayList.addAll(Arrays.asList(a));
		arrayList.add("me");

		a = arrayList.toArray(a);

		System.out.println(a.length);
	}
	@Test
	public void simpleTest() {

		int[] arr = {-10,-2,-5,-1,-8,-20};
		try {
			System.out.println(increaseBySecsUntilNewDate("15:15:00", "15:15:12"));
		}catch (ParseException excptio){
			//solution("Maison; Maison Armani; John Weak-manu; Rop; Maison", "teamapt.com");
		}
	}

	public String solution(String S, String C) {
		// email should be in lowercase
		// using first and last name
		// split the S using delimiter ; and space
		// then loop thru and get the 3 or 2 in the splited string
		// we then combine the first and last
		// and also attach the company
		// we are assuming that the company name is in format "company.com"

		LinkedHashMap<String, Integer> finalEmails = new LinkedHashMap<>() ;
		String name = "";
		String fullName = "";

		String[] names = S.split("; ");
		if (names.length == 0) return S;
		if (C.length() == 0) return S;
		for(int i = 0; i < names.length; i++){
			name = names[i];
			String[] nameSplit =  name.split(" ");
			if(nameSplit.length == 0)  return S;
			if(nameSplit.length == 1)  {
				fullName = removeInvalidCharacters(nameSplit[0]);
			}else {
				fullName = String.format("%s_%s",removeInvalidCharacters(nameSplit[0]), removeInvalidCharacters(nameSplit[nameSplit.length-1]));
			}
			String generatedEmail = (fullName+"@"+C).toLowerCase();

			if (finalEmails.containsKey(generatedEmail)){
				Integer lastIndex = finalEmails.get(generatedEmail);
				finalEmails.put(String.format("%s%d@%s",fullName, lastIndex+1, C ).toLowerCase(), 1);
				finalEmails.put(generatedEmail, lastIndex+1);
			}
			finalEmails.putIfAbsent(generatedEmail, 1);

		}


		String finalEmailsString = "";
		Integer check = 0;
		for (String keys : finalEmails.keySet()){
			if(check == 0){
				finalEmailsString = finalEmailsString.concat(keys);
			}else{
				finalEmailsString =  finalEmailsString.concat("; "+keys);
			}
			check++;
		}


		return finalEmailsString.trim();
	}

	private String removeInvalidCharacters (String dirtyString){
		return dirtyString.replaceAll("[^0-9a-zA-Z]", ""); // use regex to remove characters not in a
	}

	static int findNumberOfTriangles(int arr[]) {
		int n = arr.length;
		 // using a dual pivot sort we sort the arrays first
		Arrays.sort(arr);

		// Initialize count of triangles
		int count = 0;

		// Fix the first element. We need to run till n-3 as
		// the other two elements are selected from arr[i+1...n-1]
		for (int i = 0; i < n-2; ++i)
		{
			// Initialize index of the rightmost third element
			int k = i + 2;

			// Fix the second element
			for (int j = i+1; j < n; ++j)
			{
                /* Find the rightmost element which is smaller
                than the sum of two fixed elements
                The important thing to note here is, we use
                the previous value of k. If value of arr[i] +
                arr[j-1] was greater than arr[k], then arr[i] +
                arr[j] must be greater than k, because the
                array is sorted. */
				while (k < n && arr[i] + arr[j] > arr[k])
					++k;

            /* Total number of possible triangles that can be
                formed with the two fixed elements is k - j - 1.
                The two fixed elements are arr[i] and arr[j]. All
                elements between arr[j+1] to arr[k-1] can form a
                triangle with arr[i] and arr[j]. One is subtracted
                from k because k is incremented one extra in above
                while loop. k will always be greater than j. If j
                becomes equal to k, then above loop will increment
                k, because arr[k] + arr[i] is always/ greater than
                arr[k] */
				if(k>j) {
					count += k - j - 1;
				}
				if(count > 0){
					if(i < arr.length - 1 && j < arr.length - 1 && k < arr.length - 1){
						Integer pe = arr[i]+ arr[k]+ arr[j];
						System.out.println(pe)	;
					}
				}
			}
		}
		return count;
	}

	public int solution(int[] A) {
		// write your code in Java SE 8
		// A of size N
		// triangle is 0 <= P < Q < R
		// A[P] + A[Q] > A[R]
		// A[Q] + A[R] > A[P]
		// A[R] + A[P] > A[Q]
		// a naive solution is to loop through each and go down the in threes
		// checking if we find a match for any of the rules.
		// do i have time for the optimal solution though.
		// eg (10,2,5) is not b 10 is not less that 2 but 2 is less than 5
		// lets try to go in threes
		if (A.length < 3){
			return -1  ;

		}
		Arrays.sort(A); // lets use a dual pivot sort
		Integer greatestPerimeter = 0;
		for (int o = 0; o < A.length - 2; o++){
			if (A[o] + A[o + 1] > A[o + 2]){
				Integer newTriangePerimeter =  A[o] + A[o + 1] + A[o + 2];
				greatestPerimeter = newTriangePerimeter > greatestPerimeter ? newTriangePerimeter :  greatestPerimeter;
			}
		}
		return greatestPerimeter <= 0 ? 0 : greatestPerimeter;
	}

	public int solutions(String S, String T) {

		String[] din = S.split(":");
		Integer Hh =  Integer.valueOf(din[0]);
		Integer Mm =  Integer.valueOf(din[1]);
		Integer Ss =  Integer.valueOf(din[2]);

		String[] din2 = T.split(":");
		Integer Hh2 = Integer.valueOf(din2[0]);
		Integer Mm2 =  Integer.valueOf(din2[1]);
		Integer Ss2 =  Integer.valueOf(din2[2]);


		Integer count = 0;

		for (int i = Ss; i <= Ss2; i++){
			if (i == 60){
				Mm++;
			}
			String time = String.format("%s%s%02d", Hh, Mm, i);
			count += isInteresting(time);
		}
		return count;
	}

	private int increaseBySecsUntilNewDate(String S, String T) throws ParseException {

		SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("HH:mm:ss");
		Date date1 = simpleDateFormat.parse(S);
		Date date2 = simpleDateFormat.parse(T);


		Integer count = 0;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);

		long seconds = (date2.getTime() - date1.getTime())/1000;
		for (int increment = 0 ; increment <= seconds; increment++){
			count += isInteresting(String.format("%02d%02d%02d",
					calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)));

			calendar.add(Calendar.SECOND, 1);
		}
		return count;
	}

	private int isInteresting(String time){
		return  new HashSet(Arrays.asList(time.split(""))).size() <= 2 ? 1 : 0;
	}

	private void googleKeywordGenerator(){
				String url = "https://suggestqueries.google.com/complete/search?client=chrome&hl=en&ds=yt&gl=us&callback=__ng_jsonp__.__req59.finished&q=instrumental%20beats%20bass%20h";
				RestTemplate restTemplate= new RestTemplate((uri, httpMethod) -> {
					System.out.println(httpMethod);

					ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
					return clientHttpRequestFactory.createRequest(uri, httpMethod);
				});
		}

	// for andela


	@Test
	public void reocurance(){
		HashMap<String, Integer> map = new HashMap<>();

		String string = "helloworld";

		string = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz";
		String firstMaxString = "";


		Integer maxValue = 0;
		String maxValueString = "";
		String[] splittedString  = string.split("");

		for (Integer i =0; i < splittedString.length ; i+=2){


			String a = splittedString[i];
			String a2 = splittedString[i+1];

			if (map.containsKey(a)){
				map.put(a,map.get(a) + 1);
			}else{
				map.put(a,1);
			}

			if (maxValue <= map.get(a)){
				if(maxValue ==  map.get(a) && "" == firstMaxString){
					firstMaxString = a;
				}
				maxValue = map.get(a);
				maxValueString = a;
			}
		}

		if (map.get(firstMaxString) == maxValue){
			System.out.println(firstMaxString);
		}
		System.out.println(maxValueString);
	}

	@Test
	public void testmaximumOccurringCharacter(){
		System.out.println(maximumOccurringCharacter("YD1N35ro7iXNEYp7M3GhZ0pK9qKx4ftBaQDd1dtViBdSQTAUDkXI6Z9xDHbCf6IhQp7Na5CZt3pNDNaiRmfX4ezXHToRfiHACgWK"));
	}

	private static char maximumOccurringCharacter(String text) {
		if (text == ""){
			return '\0';
		}

		HashMap<String, Integer> map = new HashMap<>();

		String firstMaxString = "";
		Integer maxValue = 0;
		String maxValueString = "";

		for (String a : text.split("")){
			if (map.containsKey(a)){
				map.put(a,map.get(a) + 1);
			}else{
				map.put(a,1);
			}

			if (maxValue <= map.get(a)){
				if(maxValue.equals(map.get(a) != 1 ? map.get(a) - 1 : map.get(a)) && firstMaxString.isEmpty() ){
					firstMaxString = a;
				}
				maxValue = map.get(a);
				maxValueString = a;
			}
		}

		if (map.get(firstMaxString) == maxValue){
			return firstMaxString.charAt(0);
		}
		return maxValueString.charAt(0);
	}


	@Test
	public void reocuranceChar(){
		HashMap<Character, Integer> map = new HashMap<>();

		String string = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		string = "helloworld";

		Integer maxValue = 0;
		char maxValueChar = '\0';
		char firstMaxChar = '\0';

		for (String a : string.split("")){
			char charA = a.charAt(0);
			if (map.containsKey(a)){
				map.put(charA,map.get(a) + 1);
			}else{
				map.put(charA,1);
			}

			if (maxValue <= map.get(charA)){
				if(maxValue ==  map.get(charA) && '\0' == firstMaxChar){
					firstMaxChar = charA;
				}
				maxValue = map.get(charA);
				maxValueChar = charA;
			}
		}

		if (map.get(firstMaxChar) == maxValue){
			System.out.println(firstMaxChar);
		}
		System.out.println(maxValueChar);
	}



	@Test
	public  void testCanPostFix(){
		System.out.println(canPostfix("+Ma21"));
	}
	boolean canPostfix(String pre){
		return pre.contains("+")||pre.contains("-") || pre.contains("*") || pre.contains("/") ;
	}

	boolean isAnOperator(char x) {
		return  "+*/-".lastIndexOf(x) != -1;
	}

	private String preToPost(String pre_exp)
	{

		Stack<String> s = new Stack<String>();
		Integer operatorsCount = 0;
		int length = pre_exp.length();

		for (int i = length - 1; i >= 0; i--)
		{
			if (operatorsCount > 200) return "";

			if (isAnOperator(pre_exp.charAt(i)))
			{
				String op1 = s.peek(); s.pop();
				String op2 = s.peek(); s.pop();

				String temp = op1 + op2 + pre_exp.charAt(i);

				s.push(temp);
				operatorsCount++;
			}

			else
			{
				s.push( pre_exp.charAt(i)+"");
			}
		}

		return s.peek();
	}
}