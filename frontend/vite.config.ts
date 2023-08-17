import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react-swc';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  base: './',
  plugins: [react(), tsconfigPaths()],
  server: {
    proxy: {
      '/dev': {
        target: 'http://api.88hey.site:8080',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/dev/, ''),
      },
    },
  },
});
