import { createRoot } from 'react-dom/client';
import { RouterProvider } from 'react-router';
import { router } from './routes.js';

const outlet = document.getElementById('outlet');
if (outlet) {
    createRoot(outlet).render(<RouterProvider router={router} />);
}

