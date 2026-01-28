const path = require('path');
const { defineConfig } = require('vite');

process.env.UNI_PLATFORM = process.env.UNI_PLATFORM || 'h5';
process.env.UNI_INPUT_DIR = process.env.UNI_INPUT_DIR || path.resolve(__dirname, 'src');

if (!global.uniPlugin) {
  global.uniPlugin = {
    options: {},
    preprocess: {
      vueContext: {},
      nvueContext: {},
    },
    platforms: [],
  };
}

const uni = require('@dcloudio/vite-plugin-uni');
const uniPlugin = uni.default || uni;

module.exports = defineConfig({
  plugins: [uniPlugin()],
});
