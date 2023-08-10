import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react-swc';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  base: './',
  plugins: [react(), tsconfigPaths()],
  server: {
    proxy: {
      '/dev': {
        target: 'http://ec2-3-38-162-121.ap-northeast-2.compute.amazonaws.com:8080',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/dev/, ''),
      },
    },
  },
});
