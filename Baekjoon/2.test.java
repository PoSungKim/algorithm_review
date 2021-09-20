import java.util.*;

class Solution {
    public int solution(int[] student, int k) {
        int ans = 0;
        
        for(int i = 0 ; i < 1<< student.length; i++) {
            int cnt = 0, from = -1, to = -1;
            boolean indexCheck = false;
            // 조합
            for(int j = 0; j < student.length; j++) {
                if ( (i & 1 << j) != 0) {
                    // 인접 체크
                    if (from == -1 && to == -1) from = to = j;
                    else {
                        if (from - 1 == j) from = j;
                        else if (j == to + 1) to = j;
                        
                        else {
                            indexCheck = true;
                            break;
                        }
                    }
                    // 재학생 수 체크
                    if (student[j] == 1) cnt++;
                }
            }
            
            if (indexCheck == true) continue;
            if (cnt != k) continue;
            ans++;
        }
        return ans;
    }
}

import java.util.PriorityQueue;

class Solution {
    
    PriorityQueue<int[]> PQ = new PriorityQueue<>( (a,b) -> {
        if (b[0] == a[0]) return a[1] - b[1];
        return b[0] - a[0];
    });
    
    public String solution(String[] research, int n, int k) {
        int[][] countDays = new int[research.length][26];
        int std = 2 * n * k;
        
        //char별로 검색 cnt 계산
        for(int day = 0; day < research.length; day++)
            for(int i = 0; i < research[day].length(); i++) {
                char c = research[day].charAt(i);
                countDays[day][c - 'a']++;
            }
        
        int[] selectedDays = new int[26];
        int maxSelected = -1;
        
        //char별로 이슈 selected 계산
        for(int c = 0; c < 26; c++) {
            int selected = 0;
            for(int d = 0; d < research.length; d++) {
                int cnt = 0;
                boolean okay = true;
                
                if (d + n > research.length) continue;
                
                for(int d2 = d; d2 < d + n; d2++) {
                    // 매일 k번 이상 검색
                    if (countDays[d2][c] < k) {
                        okay = false;
                        break;
                    }
                    cnt += countDays[d2][c];
                }
                // 매일 k번 이상 검색 && n일동안 총 2*n*k 이상 검색
                if (okay && cnt >= std) selectedDays[c]++;
                if (maxSelected < selectedDays[c]) maxSelected = selectedDays[c];
            }
        }
        // 이슈 검색어 X
        if (maxSelected == 0) return "None";
        
        // 최고의 이슈 검색어 계산
        for(int i = 0 ; i < 26; i++) PQ.offer(new int[]{selectedDays[i], i});
        return Character.toString('a' + PQ.poll()[1]);
    }
}

import java.util.*;

class Solution {
    // 누가 누구에 신고
    Map<String, List<String>> nameToReport = new HashMap<>();
    // 신고 누적수
    Map<String, Integer> myReport = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        
        for(int i = 0; i < id_list.length; i++) 
            nameToReport.put(id_list[i], new ArrayList<String>());
        
        for(int i = 0; i < report.length; i++) {
            String[] report_list = report[i].split(" ");
            String from = report_list[0], to = report_list[1];
            
            // 한 유저가 같은 유저를 여러 번 신고한 경우는 신고 횟수 1회로 처리
            if (!nameToReport.get(from).contains(to)) {
                nameToReport.get(from).add(to);
                myReport.put(to, myReport.getOrDefault(to, 0) + 1);
            }
        }
        
        // 각 유저별로 처리 결과 메일을 받은 횟수 >> 배열
        int[] answer = new int[id_list.length];
        
        int curId = 0;
        for(String name : id_list) {
            for(String reportedName : nameToReport.get(name)) 
                if (myReport.get(reportedName) >= k) 
                    answer[curId]++;
            
            curId++;
        }
        
        return answer;
    }
}

import java.util.*;

class Solution {
    Map<String, List<Integer>> IN = new TreeMap<>();
    Map<String, List<Integer>> OUT = new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        
        for(String record : records) {
            
            String[] r = record.split(" ");
            
            String time = r[0], car = r[1], status = r[2];
            int hour = Integer.parseInt(time.split(":")[0]) * 60,
                min =  Integer.parseInt(time.split(":")[1]),
                totalSec = hour + min;
            
            if (status.equals("IN")) {
                if (IN.containsKey(car))
                    IN.get(car).add(totalSec);
                else {
                    IN.put(car,  new ArrayList<>());
                    IN.get(car).add(totalSec);
                }
            }
            else {
                if (OUT.containsKey(car))
                    OUT.get(car).add(totalSec);
                else {
                    OUT.put(car,  new ArrayList<>());
                    OUT.get(car).add(totalSec);
                }
            }
                
        }
        
        int[] answer = new int [IN.size()];
        int idx = 0;
        for( String car : IN.keySet()) {
            int totalTime = 0;
            for(int i = 0; i < IN.get(car).size(); i++ ) {
                int from = IN.get(car).get(i);
                int to = (OUT.containsKey(car) && OUT.get(car).size() > i) ? 
                          OUT.get(car).get(i) : 
                          23 * 60 + 59;
                
                totalTime += to - from;
            }
            answer[idx]   += fees[1];
            answer[idx++] += (totalTime > fees[0]) ? Math.ceil( ( (double)totalTime - fees[0]) / fees[2]) * fees[3] : 0;
        }
        
        return answer;
    }
}

import java.util.*;

class Solution {

    Map<String, List<Integer>> idxMap = new LinkedHashMap<>();
    String origin;
    List<String> List;
    
    boolean isPrime(int n) {
        
        if (Integer.toString(n).contains("0")) return false;
        
        if (n == 1 || n == 0) return false;
        
        for(int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        
        return true;
    }
    
    int OPO() {
        int cnt = 0;
        List<String> tmp = new ArrayList<>();
        
        if (List.size() <= 0 ) return 0;
        
        for(String opt : List) {
            String opt2 =  "0" + opt + "0";
            
            if (idxMap.containsKey(opt) && idxMap.get(opt).size() <= 0) continue;
            
            if (origin.indexOf(opt2, idxMap.get(opt).get(0) - 1) != -1) {
                if (opt.length() > 0 && isPrime(Integer.parseInt(opt))) {
                    cnt++;
                    tmp.add(opt);
                }
                idxMap.get(opt).remove(0);
            }
            
        }
        for(String tStr : tmp)
            if (List.contains(tStr))
                List.remove(tStr);
        
        return cnt;
    }
    
    int OP() {
        int cnt = 0;
        List<String> tmp = new ArrayList<>();
        
        if (List.size() <= 0 ) return 0;
        
        for(String opt : List) {
            String opt2 = "0" + opt;
            
            if (idxMap.containsKey(opt) && idxMap.get(opt).size() <= 0) continue;
            
            if (origin.indexOf(opt2, idxMap.get(opt).get(0) - 1) != -1) {
                if (opt.length() > 0 && isPrime(Integer.parseInt(opt))) {
                    cnt++;
                    tmp.add(opt);
                }
                idxMap.get(opt).remove(0);
            }
        }
        
        for(String tStr : tmp)
            if (List.contains(tStr))
                List.remove(tStr);
        
        return cnt;
    }
    
    int PO() {
        int cnt = 0;
        List<String> tmp = new ArrayList<>();
        
        if (List.size() <= 0 ) return 0;
        
        for(String opt : List) {
            String opt2 =  opt + "0";
            
            if (idxMap.containsKey(opt) && idxMap.get(opt).size() <= 0) continue;
            
            if (origin.indexOf(opt2, idxMap.get(opt).get(0)) != -1) {
                if (opt.length() > 0 && isPrime(Integer.parseInt(opt))) {
                    cnt++;
                    tmp.add(opt);
                }
                idxMap.get(opt).remove(0);
            }
        }
        
        for(String tStr : tmp)
            if (List.contains(tStr))
                List.remove(tStr);
        
        return cnt;
    }
    
    int P() {
        
        int cnt = 0;
        List<String> tmp = new ArrayList<>();
        
        if (List.size() <= 0 ) return 0;
        
        for(String opt : List) {
            String opt2 =  "" + opt + "";
            if (idxMap.containsKey(opt) && idxMap.get(opt).size() <= 0) continue;
            if (!(idxMap.containsKey(opt))) continue;
            
            if (origin.indexOf(opt2, idxMap.get(opt).get(0)) != -1) {
                if (opt.length() > 0 && isPrime(Integer.parseInt(opt))) {
                    cnt++;
                }
            }
        }
        
        
        return cnt;
    }
    
    String conversion(int n, int k){
        String conv = "";
        while(n > 0){
            conv = (n % k) + conv;
            n /= k;
        }
        
        return conv;
    }
    
    public int solution(int n, int k) {
        origin = conversion(n, k);
        
        String [] strList = origin.split("0");
        
        List = new ArrayList<>();
        for(String str : strList) 
            if (str.length() >)
                if (isPrime(Integer.parseInt(str)))
                    List.add(str);
        
        int curIdx = 0;
        for(String str : List) {
            if (!idxMap.containsKey(str)) idxMap.put(str, new ArrayList<Integer>());
            
            idxMap.get(str).add(origin.indexOf(str, curIdx));
            curIdx = idxMap.get(str).get(idxMap.get(str).size() - 1) + str.length() ;
        }
        
        int cnt = 0;
        cnt += OPO();
        cnt += PO();
        cnt += OP();
        cnt += P();
        
        return cnt;

    }
}