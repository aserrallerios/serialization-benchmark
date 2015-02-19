# Serialization & Deserialization benchmark for java

This code can be used to easily compare serialization providers and collections (or any Object), and thus find the faster providers and collections for serialization/deserialization and the most efficient ones space-wise. 
To implement new serialization providers and collections is straight forward following the interfaces signatures. 

Benchmark parameters are:
* Times: The number of times the operation is executed
* Size: Logical measurement of size for the collection to be serialized/deserialized. Useless if benchmarking constant-size objects.

Benchmark results:
* The time it takes for each serializer to:
    * Serialize {Times} times the collection of {Size} size
    * Deserialize {Times} times the collection of {Size} size
    * Size in bytes of the serialized collection of {Size} size
