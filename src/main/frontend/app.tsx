import { RouterProvider } from 'react-router';
import { router } from './routes.js';
import { createRoot } from 'react-dom/client';
import { AuthProvider } from './auth.js';

const outlet = document.getElementById('outlet');
if (outlet) {
    createRoot(outlet).render(
        <AuthProvider>
            <RouterProvider router={router} />
        </AuthProvider>
    );
}
