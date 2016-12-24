## Working to build a simple term-documented index matrix based on the information in [Introduction to Information Retrieval](http://nlp.stanford.edu/IR-book/html/htmledition/irbook.html "Title").
### This comes specifically from the [Boolean Retrieval](http://nlp.stanford.edu/IR-book/html/htmledition/boolean-retrieval-1.html "Title") segment.

--------------------

### Sample output:
####Document Messages:
	* Document 1: My name is Lyle, and I am cool.
	* Document 2: My name is Bob, and I am awesome.
	Document 3: The quick brown fox jumps over the lazy dog.

	Document Word Sets:
	Document 1: [am, and, cool, i, is, lyle, my, name]

	Document 2: [am, and, awesome, bob, i, is, my, name]

	Document 3: [brown, dog, fox, jumps, lazy, over, quick, the]


	Map keys:   am       and      awesome  bob      brown    cool     dog      fox      i        is       jumps    lazy     lyle     my       name     over     quick    the      

	Document Term Matrix:

	Document 1: 1        1        0        0        0        1        0        0        1        1        0        0        1        1        1        0        0        0        

	Document 2: 1        1        1        1        0        0        0        0        1        1        0        0        0        1        1        0        0        0        

	Document 3: 0        0        0        0        1        0        1        1        0        0        1        1        0        0        0        1        1        1        


	Which documents have this word: 'name'

	Found 'name' in Document 1: My name is Lyle, and I am cool.

	Found 'name' in Document 2: My name is Bob, and I am awesome.


	Which documents have this word: 'fox'

	Found 'fox' in Document 3: The quick brown fox jumps over the lazy dog.

	Process finished with exit code 0