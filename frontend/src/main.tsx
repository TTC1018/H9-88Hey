import ReactDOM from 'react-dom/client';

import { App } from './App.tsx';

// import { initMocks } from './mocks/index.ts';

// if (process.env.NODE_ENV === 'development') {
//   await initMocks();
// }

ReactDOM.createRoot(document.getElementById('root')!).render(<App />);
