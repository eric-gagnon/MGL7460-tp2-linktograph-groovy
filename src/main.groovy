
app = new myfirstgroovyapp()
app.hellogroovy()

myFunc(2, 3, param1: 1)

def myFunc(Map attrs, param2, param3) {
    println(attrs.param1)
    println(param2)
    println(param3)
}

def mymap() {
    final map = [attrib1: "myvalue", attrib2: "myvalue2"]
}

def mymaptoclasstest mymap2() {
    new mymaptoclasstest(attrib1: "myvalue", attrib2: "myvalue2")
}

def hashMap = [attrib1: "myvalue", attrib2: "myvalue2"]

[55, 127, -9, -100, 568]