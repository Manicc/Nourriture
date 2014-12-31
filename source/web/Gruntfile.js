module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        karma: {
            unit: {
                configFile: 'test/karma.conf.js'
            },
            //continuous integration mode: run tests once in PhantomJS browser.
            continuous: {
                configFile: 'test/karma.conf.js',
                singleRun: true,
                browsers: ['PhantomJS']
            }
        }
    });

    // Load the plugin that provides the "karma" task.
    grunt.loadNpmTasks('grunt-karma');

    // Default task(s).
    grunt.registerTask('default', ['karma:unit']);
    grunt.registerTask('ci', ['karma:continuous']);

};