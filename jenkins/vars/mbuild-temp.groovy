def call(args){
    sh "${tool 'M3'}/bin/mvn ${args}"
}
//null line is required