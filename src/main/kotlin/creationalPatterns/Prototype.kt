package creationalPatterns


interface Cloneable {
    fun clone(): Any
}

class Employee() : Cloneable {
    private var empList = mutableListOf<String>()

    constructor(empList: MutableList<String>) : this() {
        this.empList = empList
    }

    fun loadData() {
        empList.add("Pankaj");
        empList.add("Raj");
        empList.add("David");
        empList.add("Lisa");
    }

    fun getEmpList() : MutableList<String> = empList
    override fun clone(): Employee{
        val temp = mutableListOf<String>();
        for (s in empList) {
            temp.add(s)
        }
        return Employee(temp)
    }
}

fun main() {
    val emps = Employee()
    emps.loadData()

    val empsNew: Employee = emps.clone()
    val empsNew1: Employee = emps.clone()
    val list: MutableList<String> = empsNew.getEmpList()
    list.add("John")
    val list1: MutableList<String> = empsNew1.getEmpList()
    list1.remove("Pankaj")

    System.out.println("emps List: " + emps.getEmpList())
    println("empsNew List: $list")
    println("empsNew1 List: $list1")
}