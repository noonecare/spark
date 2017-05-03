val enabled = false

// ByName parameter
def myAssert(a: =>Boolean)
{
    if( enabled && a ){
        println("yes")
    }
    else
    {
        println("no")
    }
}

myAssert(1 == 1)
myAssert( 1/0 == 1)
