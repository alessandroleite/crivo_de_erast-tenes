Number.metaClass.from = {  delegate }

Number.metaClass.to = { value ->
	(delegate .. value).flatten()
}

Number.metaClass.sqrt = {
	->
	Math.sqrt(delegate)
}

Number.metaClass.square = { -> delegate * delegate }

def primes = CrivoEratostenes.&primes

static int[] primes(from = 0, to) {
	def p = (0 .. to).flatten()
	
	if (to == 2)
		return [to]

	if (from == 2 && to == 3)
		return [2, 3]
	
	([2, (3 .. to.sqrt()).step(2)]).flatten().each { n->
		def L = ((n.square() .. to).step(n * (n == 2 ? 1 : 2))).each { i ->
			p[i] = 0
		}
	}

	return p.findAll({it != null && it >= from && it != 0 && it != 1});
}

assert [] == primes (0)

assert [] == primes (1)

assert [2] == primes(2)

assert [2, 3] == primes(2, 3)

assert [2,3,5,7] == primes (10)

assert [2, 3, 5, 7, 11, 13, 17, 19, 23, 29] == primes (1,30)

assert [71, 73, 79, 83, 89, 97] == primes (70, 100)

assert primes(0, 30) == primes(1, 30)