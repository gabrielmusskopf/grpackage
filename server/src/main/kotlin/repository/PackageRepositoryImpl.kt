package repository

import domain.Package

class PackageRepositoryImpl : PackageRepository {

    private val packages: MutableMap<Long, Package> = mutableMapOf()

    private fun nextId(): Long = packages.size.toLong() + 1

    override fun getAll(): List<Package> = packages.values.toList()

    override fun save(pkg: Package): Package {
        if (pkg.id != null && packages.containsKey(pkg.id)) {
            //TODO: update
            return pkg
        }

        val id = nextId()
        pkg.id = id
        packages[id] = pkg

        return pkg
    }

    override fun getById(id: Long) = packages[id]

}
