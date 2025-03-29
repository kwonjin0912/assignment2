package com.example.myapplication

private fun <T> Set<T>.skip(n: Int): List<T>
{
    return this.withIndex().filter { it.index % n == 0 }.map { it.value }
}

open class BcsdLabUser(var name : String, var emoji : String){

    open fun printwhat(){
        println("${name} ${emoji}")
    }
}
class Beginner(name: String, val assignment1 : Int) : BcsdLabUser(name, "새싹"){
    var assignment : Int = assignment1
    override fun printwhat(){
        println("비기너 = ${name} ${emoji} - 과제 안한 횟수 : ${assignment}")
    }
}
open class Regular(name: String, val pay1: Boolean) : BcsdLabUser(name, "사과"){
    var pay : Boolean = pay1
    override fun printwhat(){
        println("레귤러 = ${name} ${emoji} - 이번달 회비를 냄: ${pay}")
    }
}
open class Mentor(name: String, val email1: String ?= null) : BcsdLabUser(name, "별"){
    var email : String? = email1
    override fun printwhat(){
        println("멘토 = ${name} ${emoji} - 이메일 : ${email ?: "없음"}")
    }
}
fun main() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(2, 3, 4)

    val list3 = list1.union(list2)

    println(list3)

    val result = list3.skip(2)
    println(result)

    val users: List<BcsdLabUser> = listOf(
        Beginner("권진", 3),
        Beginner("김보성", 0),
        Regular("김도훈", false),
        Regular("김상진", true),
        Mentor("김영찬", "zerochan@naver.com"),
        Mentor("양석현") // 이메일 없는 멘토
    )
    print("\n")
    for (user in users) {
        user.printwhat()
    }

    val beginners = users.filterIsInstance<Beginner>()

    println("\n모든 비기너들:")
    for (beginner in beginners) {
        println("${beginner.name} ${beginner.emoji}")
    }

    val beginnerassignment = users.filterIsInstance<Beginner>().filter { it.assignment >= 3 }

    println("\n과제를 3회 이상 수행하지 못한 비기너들:")
    for (beginner in beginnerassignment) {
        println("${beginner.name} ${beginner.emoji}")
    }

    val regularpay = users.filterIsInstance<Regular>().filter { it.pay == false }
    println("\n이번달 회비를 내지 않은 레귤러들:")
    for (regular in regularpay){
        println("${regular.name} ${regular.emoji}")
    }

    val firstname = users.filterIsInstance<BcsdLabUser>().filter { it.name.startsWith("양")}
    println("\n성이 양씨인 사람들")
    for(user in firstname) {
        println("${user.name} ${user.emoji}")
    }

}