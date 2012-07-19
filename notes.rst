LiquidformQueries
=================

Easy and concise.

Cannot handle properties of primitive type.

Requires all properties to have public getters with sensible names. Does not stop you using methods which aren't properties.

No common base type for queries. SubQuery is close, but excludes OrderByClause.

Possible to gave valid syntax but still get ``java.lang.IllegalStateException: Nothing is known about null``. This was due to forgetting an ``as``.

Joins can go wrong if you declare the alias wrong. Needs to be an alias of the property used for the join, not the general type. Type system does not prevent this.

Have to name the join property twice when joining (in the alias and the join). This gets irritating.

RawCriteriaQueries
==================

Nobody would ever use this, so problems don't really matter.

Verbose, like all JPA criteria stuff.

The need to say ``author.joinSet("ideas")`` was a bit surprising. There is a ``join(SetAttribute)``, but i couldn't find a way to get a suitable SetAttribute instance.

MetamodelCriteriaQueries
========================

The need to correlate attributes for subqueries was pretty unexpected.

QueryDSL
========

Has some sort of Scala wrapper.

Backends for things other than JPA - raw SQL, Mongo, Lucene, etc.

Can't do correlated subqueries as nicely - have to start with a simple root

General
=======

When writing a complex query, i tried to keep it on one big piece, like a JPQL query. But is this the right way to do it? You wouldn't do it for any other kind of code. There's a lot of complexity in there.
