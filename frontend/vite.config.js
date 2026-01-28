const path = require('path');
const vite = require('vite');
const { defineConfig } = vite;
const { createFilter } = require('@rollup/pluginutils');

process.env.UNI_PLATFORM = process.env.UNI_PLATFORM || 'h5';
process.env.UNI_INPUT_DIR = process.env.UNI_INPUT_DIR || path.resolve(__dirname, 'src');

if (typeof vite.createFilter !== 'function') {
  // CJS Vite API lacks createFilter; @vitejs/plugin-vue expects it.
  vite.createFilter = createFilter;
}

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
  logLevel: 'info',
  plugins: [uniPlugin()],
});
