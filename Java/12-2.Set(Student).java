import java.util.*;

class Student implements Comparable<Student> {
        
    String name;
    Integer id; 

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student curObj = (Student)o;
            return this.getId() == curObj.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public int compareTo(Student a) {
        return a.getId() - this.getId();
    }

    @Override
    public String toString() {
        return this.getName() + "[" + this.getId() + "]";
    }
}

class Solution {

    TreeSet<Student> Set = new TreeSet<>();

    public int solution(int X, int Y, int D) {
        Set.add(new Student("Kim", 1)); // [Kim[1]]
        Set.add(new Student("Kim", 2)); // [Kim[2], Kim[1]]
        Set.add(new Student("Lee", 3)); // [Lee[3], Kim[2], Kim[1]]
        
        Set.add(new Student("Lee", 1)); // [Lee[3], Kim[2], Kim[1]] >> 1 is already taken
        Set.add(new Student("Lee", 2)); // [Lee[3], Kim[2], Kim[1]] >> 2 is already taken
        Set.add(new Student("Kim", 3)); // [Lee[3], Kim[2], Kim[1]] >> 3 is already taken

        System.out.println(Set);

        return 0;
    }
}
