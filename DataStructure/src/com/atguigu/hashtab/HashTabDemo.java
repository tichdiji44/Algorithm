package com.atguigu.hashtab;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {

        HashTable hashTable = new HashTable(6);

        int key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1:  添加雇员");
            System.out.println("2: 显示雇员");
            System.out.println("3: 查找雇员");
            System.out.println("4: 删除雇员");
            System.out.println("5: 找到链表长度");
            System.out.println("6: 修改雇员");
            System.out.println("7: 退出");

            key = scanner.nextInt();
            switch (key) {
                case 1:
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    System.out.println("输入地址");
                    String address = scanner.next();
                    // 创建 雇员
                    Employee emp = new Employee(id, name, address);
                    hashTable.add(emp);
                    break;
                case 2:
                    hashTable.list();
                    break;
                case 3:
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case 4:
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.deleteEmpById(id);
                    ;
                    break;
                case 5:
                    System.out.println("请输入要查找的链表的长度");
                    id = scanner.nextInt();
                    int length = hashTable.hashTableLeagth(id);
                    System.out.println("链表长度为" + length);
                    break;
                case 6:
                    System.out.println("请输入要修改的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    System.out.println("输入名字");
                    String upadte_name = scanner.next();
                    System.out.println("输入地址");
                    String upadte_address = scanner.next();
                    hashTable.updateEmpById(id, upadte_name, upadte_address);
                    ;
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }

}

// 创建hashtable
class HashTable {
    private EmpLinkedList[] empLinkedLists;

    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void add(Employee employee) {
        int empLinkedListNo = hashFun(employee.id);
        empLinkedLists[empLinkedListNo].add(employee);
    }

    // 遍历所有的链表 遍历hashtable
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }

    }

    public int hashFun(int id) {
        return id % size;
    }

    // 根据输入的id,查找雇员
    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Employee emp = empLinkedLists[empLinkedListNO].findEmpById(id);
        if (emp != null) {// 找到
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    // 根据输入的id,修改雇员
    public void updateEmpById(int id, String name, String address) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Employee emp = empLinkedLists[empLinkedListNO].updateEmpById(id, name, address);
        if (emp != null) {// 找到
            System.out.printf("在第%d条链表中修改 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    // 删除
    public void deleteEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Employee emp = empLinkedLists[empLinkedListNO].deleteByID(id);
        if (emp != null) {// 找到
            System.out.printf("在第%d条链表中删除 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    // 长度
    public int hashTableLeagth(int id) {
        if (id < size) {
            int empLinkedListNO = hashFun(id);
            int length = empLinkedLists[empLinkedListNO].length();
            return length;
        } else {
            System.out.println("hashtable最大只有" + size + "条列表");
            return size;
        }

    }
}

class Employee {
    public int id;
    public String name;
    public String address;
    public Employee next;

    public Employee(int id, String name, String address) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
    }
}

class EmpLinkedList {
    // 设置一个头指针headEmployee
    // 执行第一个Employee，因此我们这个链表的head 是指向第一个employee
    private Employee headEmployee;

    // 添加雇员到链表
    // 说明
    // 1.假定 当添加雇员 id是自增加
    public void add(Employee employee) {
        if (headEmployee == null) {
            headEmployee = employee;
            return;
        }
        Employee curEmployee = headEmployee;
        while (true) {
            if (curEmployee.next == null) {
                break;
            }
            curEmployee = curEmployee.next;
        }
        curEmployee.next = employee;
    }

    // 遍历
    public void list() {
        if (headEmployee == null) {
            System.out.println("当前链表为空");
            return;
        }
        System.out.println("当前链表信息为");
        Employee curEmployee = headEmployee;
        while (true) {
            System.out.printf("--> id=%d name=%s,address=%s\t", curEmployee.id, curEmployee.name, curEmployee.address);
            if (curEmployee.next == null) {
                break;
            }
            curEmployee = curEmployee.next;
        }
        System.out.println();

    }

    // 找到该员工信息
    public Employee findEmpById(int id) {
        // 判断链表是否为空
        if (headEmployee == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Employee curEmp = headEmployee;
        while (true) {
            if (curEmp.id == id) {// 找到
                break;// 这时curEmp就指向要查找的雇员
            }
            // 退出
            if (curEmp.next == null) {// 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;// 以后
        }

        return curEmp;
    }

    // 修改该员工信息
    public Employee updateEmpById(int id, String name, String address) {
        // 判断链表是否为空
        if (headEmployee == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Employee curEmp = headEmployee;
        while (true) {
            if (curEmp.id == id) {// 找到
                break;// 这时curEmp就指向要查找的雇员
            }
            // 退出
            if (curEmp.next == null) {// 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;// 以后
        }
        curEmp.name = name;
        curEmp.address = address;
        return curEmp;
    }

    // 删除该员工
    public Employee deleteByID(int id) {
        if (headEmployee == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Employee curEmp = headEmployee;
        Employee indexEmp = headEmployee;
        int x = 0;
        for (int i = 0; i < length(); i++) {
            x = i;
            if (curEmp.id == id) {// 找到
                break;// 这时curEmp就指向要查找的雇员
            }
            curEmp = curEmp.next;

        }

        for (int i = 0; i < length(); i++) {
            if (x == 0) {
                headEmployee = curEmp.next;
            }
            if (i == (x - 1)) {
                indexEmp.next = curEmp.next;
                // indexEmp.next = indexEmp.next.next;
                break;
            }
            indexEmp = indexEmp.next;

        }
        return curEmp;

    }

    // 找到hashtable里数组中链表的长度
    public int length() {
        if (headEmployee == null) {
            System.out.println("链表为空");
            return 0;
        }
        // 辅助指针
        Employee curEmp = headEmployee;
        int length = 1;
        while (true) {
            // 退出
            if (curEmp.next == null) {// 说明遍历当前链表没有找到该雇员
                break;
            }
            curEmp = curEmp.next;// 以后
            length++;
        }
        return length;
    }


}
