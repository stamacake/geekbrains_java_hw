import java.util.ArrayList;

class Employee{
    private String name;
    private String email;
    private int age;
    private String position;

    public Employee(String _name, String _email, int _age, String _position){
        this.name=_name;
        this.email=_email;
        this.age=_age;
        this.position=_position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void employeePrint(){
        System.out.print(this.name + " : ");
        System.out.print(this.email + " : ");
        System.out.print(this.age + " : ");
        System.out.println(this.position);
    }
}

class Group{
    String groupName;
    ArrayList<Employee> employees;

    public Group(String _groupName, ArrayList<Employee> _employees){
        this.groupName=_groupName;
        this.employees = _employees;
    }

    public void deleteAllEmployees(){
        this.employees.clear();
    }

    public void addEmployee(Employee _employee){
        if(this.employees.size()<10) {
            this.employees.add(_employee);
        } else
            System.out.println("Employees limit");
    }

    public void deleteEmployee(int indx){
        if(indx<this.employees.size()-1){
            this.employees.remove(indx);
        } else System.out.println("no such index");
    }

    public void printGroup(){
        System.out.println("Group: "  + this.groupName);
        if(this.employees.size()>0) {
            for (Employee x : this.employees) {
                System.out.print("name:" + x.getName());
                System.out.print(" age:" + x.getAge());
                System.out.println(" position:" + x.getPosition());
            }
        } else System.out.println("Group empty");
    }
}

public class Hw2 {
    public static void main(String[] args) {

        Employee imp1 = new Employee("Antony", "qqq@mail.ru", 22, "developer");
        Employee imp2 = new Employee("Charles","www@ya.ru", 23, "devOps engineer");
        ArrayList<Employee> imps= new ArrayList<Employee>();
        imps.add(imp1);
        imps.add(imp2);

        imp1.employeePrint();
        imp2.employeePrint();
        System.out.println();
        Group team = new Group("First Team", imps);
        Employee imp3 = new Employee("Peter","eee@gmail.com", 30, "TeamLead");
        team.printGroup();
        System.out.println();
        team.addEmployee(imp3);
        team.printGroup();
        System.out.println();
        team.deleteEmployee(0);
        team.printGroup();
        System.out.println();
        team.deleteEmployee(4);
        System.out.println();
        team.deleteAllEmployees();
        team.printGroup();
    }

}
