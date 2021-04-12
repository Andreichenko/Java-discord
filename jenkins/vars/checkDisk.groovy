columns = ["path","size","used","free","used%"]
println columns.join("\t")

result = true;

File.listRoots().each{
    if (!"".equals(root) && !root.equals(it.getAbsolutePath())) return
    percent = Math.ceil((it.getTotalSpace() - it.getFreeSpace()) * 100 / it.getTotalSpace())
    columns = []
    columns << it.getAbsolutePath()
    columns << Math.ceil(it.getTotalSpace() /1024/1024)
    columns << Math.ceil((it.getTotalSpace() - it.getFreeSpace()) /1024/1024)
    columns << Math.ceil(it.getFreeSpace() /1024/1024)
    columns << percent + "%"

    println columns.join("\t")

    if (percent >= Double.valueOf(threshold)) { result = false }
}

return result