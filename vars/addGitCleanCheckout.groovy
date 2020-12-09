
//need to rework after test
private java.lang.Boolean hasCheckOutPlugin(plugin, java.lang.Boolean b) {
    for (plugins in plugin) {
        if (plugins instanceof hudson.plugins.git.extensions.impl.CleanCheckout) {
            return true;
        }
    }
    return false;
}
