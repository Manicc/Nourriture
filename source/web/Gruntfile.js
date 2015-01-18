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
        },
        protractor: {
            options: {
                configFile: "test/protractor.conf.js", // Default config file
                keepAlive: false, // If false, the grunt process stops when the test fails.
                noColor: false, // If true, protractor will not use colors in its output.
                args: {
                    // Arguments passed to the command
                }
            },
            default: {   // Grunt requires at least one target to run so you can simply put 'all: {}' here too.
            }
        }
    });

    // Load the plugin that provides the "karma" task.
    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-protractor-runner');

    // Default task(s).
    grunt.registerTask('default', ['karma:unit']);
    grunt.registerTask('ci', ['karma:continuous']);
    grunt.registerTask('e2e', ['protractor:default']);
};