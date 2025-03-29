package com.example.myapplication

private fun <T> Set<T>.skip(n: Int): List<T>
{
    return this.withIndex().filter { it.index % n == 0 }.map { it.value }
}

sealed class BcsdLabUser(var name : String, var emoji : String){

    class Beginner(name: String, val assignment : Int) : BcsdLabUser(name, "새싹")
    class Regular(name: String, val pay: Boolean) : BcsdLabUser(name, "사과")
    class Mentor(name: String, val email: String ?= null) : BcsdLabUser(name, "별")
}
open class Beginner(){
    var assignment : Int = 0
}
open class Regular(){
    var pay : Boolean = true
}
open class Mentor(){
    var email : String? = null
}
fun main() {
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(2, 3, 4)

    val list3 = list1.union(list2)

    println(list3)

    val result = list3.skip(2)
    println(result)

    val users: List<BcsdLabUser> = listOf(
        BcsdLabUser.Beginner("권진", 3),
        BcsdLabUser.Beginner("김보성", 0),
        BcsdLabUser.Regular("김도훈", false),
        BcsdLabUser.Regular("김상진", true),
        BcsdLabUser.Mentor("김영찬", "zerochan@naver.com"),
        BcsdLabUser.Mentor("양석현") // 이메일 없는 멘토
    )
    print("\n")
    for (user in users)
    {
        when (user)
        {
            is BcsdLabUser.Beginner -> println("비기너 = ${user.name} ${user.emoji} - 과제 안한 횟수 : ${user.assignment}")
            is BcsdLabUser.Regular -> println("레귤러 = ${user.name} ${user.emoji} - 이번달 회비를 냄: ${user.pay}")
            is BcsdLabUser.Mentor -> println("멘토 = ${user.name} ${user.emoji} - 이메일 : ${user.email ?: "없음"}")
        }
    }
    val beginners = users.filterIsInstance<BcsdLabUser.Beginner>()

    println("\n모든 비기너들:")
    for (beginner in beginners) {
        println("${beginner.name} ${beginner.emoji}")
    }

    val beginnerassignment = users.filterIsInstance<BcsdLabUser.Beginner>().filter { it.assignment >= 3 }

    println("\n과제를 3회 이상 수행하지 못한 비기너들:")
    for (beginner in beginnerassignment) {
        println("${beginner.name} ${beginner.emoji}")
    }

    val regularpay = users.filterIsInstance<BcsdLabUser.Regular>().filter { it.pay == false }
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



