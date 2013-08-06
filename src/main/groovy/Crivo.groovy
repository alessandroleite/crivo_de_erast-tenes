Number.metaClass.sqrt = {
	->
	Math.sqrt(delegate)
}

Number.metaClass.floor = {
	-> Math.floor(delegate)
}

java.util.List.metaClass.removerMultiplosDe = {value ->
	delegate.removeAll(delegate.findAll({obj -> obj % value == 0}))
}

java.util.List.metaClass.removeFirst = {
	
	if (!delegate.isEmpty())
	{
		def l = [delegate].flatten()
		delegate.clear()
		delegate.addAll(l.tail())
		return l[0]
	}
	return null
}


def primes = Crivo.&primes

static Integer[] primes(limit) 
{
	def maiorValorASerChecado = limit.sqrt().floor()
	def listaInteirosDe2AteLimit = (2 .. limit).flatten()
	def p = [listaInteirosDe2AteLimit[0]]

	for (i in 2..maiorValorASerChecado) 
	{
		listaInteirosDe2AteLimit.removerMultiplosDe(p.last())
		if (listaInteirosDe2AteLimit[0])
		{
			p = [p, listaInteirosDe2AteLimit.removeFirst()].flatten()
		}
	}
	return [p, listaInteirosDe2AteLimit].flatten();
}

assert [2] == primes(2)
assert [2, 3] == primes(3)
assert [2, 3, 5] == primes(5)
assert [2, 3, 5, 7] == primes(7)
assert [2, 3, 5, 7] == primes (10)
assert [2, 3, 5, 7, 11, 13, 17, 19, 23, 29] == primes (30)