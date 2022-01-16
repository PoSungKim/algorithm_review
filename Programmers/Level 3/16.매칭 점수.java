// String.split() 활용
// String body = page.split("<body>")[1].split("</body>")[0];

// //S : 공백을 제외한 모든 문자 활용
// String rule = "<meta property=\"og:url\" content=\"https://(\\S*)\"";
// String rule = "<a href=\"https://(\\S*)\"";
// 정규식 유용글 : https://coding-factory.tistory.com/529


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

class Solution {
    
    class Page {
        String page, myDomain;
        int pk, outerNum = 0, basicPt = 0;
        double linkPt = 0, matchPt = 0;
        List<String> outerLinks = new ArrayList<>();
        
        public Page(int pk, String page) {
            this.pk         = pk;
            this.page       = page;
            this.myDomain   = getMyDomain();
            this.outerLinks = getOuterLinks();
        }
        
        public void getBasicPt(String word) {
            String body = page.split("<body>")[1].split("</body>")[0];
            body = body.replaceAll("[0-9]", " ");
            
            String rule = "\\b" + word + "\\b";
            Pattern pattern = Pattern.compile(rule);
            Matcher matcher = pattern.matcher(body);
            
            while(matcher.find())
                basicPt++;
        }
        
        private List<String> getOuterLinks() {
            List<String> tmp = new ArrayList<>();
            String rule = "<a href=\"https://(\\S*)\"";
            Pattern pattern = Pattern.compile(rule);
            Matcher matcher = pattern.matcher(page);
            while(matcher.find())
                tmp.add(matcher.group(1));
            
            outerNum = tmp.size();
            return tmp;
        }
        
        private String getMyDomain() {
            String rule = "<meta property=\"og:url\" content=\"https://(\\S*)\"";
            Pattern pattern = Pattern.compile(rule);
            Matcher matcher = pattern.matcher(page);
            
            if (matcher.find())
                return matcher.group(1);

            return "";
        }
    }
    
    List<Page> pageList = new ArrayList<>();
    
    public int solution(String word, String[] pages) {
        int answer = 0, idx = 0;
        for(String page : pages){
            Page curPage = new Page(idx++, page.toLowerCase());
            curPage.getBasicPt(word.toLowerCase());
            pageList.add(curPage);
        }
        
        for(Page page1 : pageList) {
            for(Page page2 : pageList) {
                if (page1.myDomain.equals(page2.myDomain)) continue;
                
                if (page2.outerLinks.contains(page1.myDomain)) 
                    page1.linkPt += (double)page2.basicPt / page2.outerNum;
            }
        }
        
        for(Page page : pageList) 
            page.matchPt = page.basicPt + page.linkPt;
        
        Collections.sort(pageList, (a, b)-> {
            if (a.matchPt == b.matchPt) return a.pk - b.pk;
            
            if (a.matchPt < b.matchPt)  return 1;
            else return -1;
        });
        
        return pageList.get(0).pk;
    }
}
