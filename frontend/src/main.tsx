import ReactDOM from 'react-dom/client';

import { App } from './App';
import { AuthProvider } from './AuthProvider';

import './styles/font.css';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <AuthProvider>
    <App />
  </AuthProvider>
);
