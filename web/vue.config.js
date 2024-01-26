const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath: './',
  outputDir: 'dist', // dist
  assetsDir: 'static',
  transpileDependencies: true
})
