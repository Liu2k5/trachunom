import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { useState } from 'react';

export const config: ViewConfig = {
    route: 'login',
    menu: { exclude: true },
    title: 'Đăng nhập',
};

export default function LoginView() {
    const [error, setError] = useState('');

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const form = e.currentTarget;
        const username = (form.elements.namedItem('username') as HTMLInputElement).value;
        const password = (form.elements.namedItem('password') as HTMLInputElement).value;
        setError('');

        // Lấy CSRF token từ cookie nếu có
        const csrfToken = document.cookie
            .split(';')
            .map(c => c.trim())
            .find(c => c.startsWith('XSRF-TOKEN='))
            ?.split('=')[1];

        const body = new URLSearchParams();
        body.append('username', username);
        body.append('password', password);

        const headers: Record<string, string> = {
            'Content-Type': 'application/x-www-form-urlencoded',
        };
        if (csrfToken) {
            headers['X-XSRF-TOKEN'] = csrfToken;
        }

        const response = await fetch('/login', {
            method: 'POST',
            headers,
            body: body.toString(),
            credentials: 'same-origin',
        });

        if (response.ok || response.redirected) {
            window.location.href = '/';
        } else {
            setError('Tên đăng nhập hoặc mật khẩu không đúng');
        }
    };

    return (
        <div style={{
            width: '100%', height: '100vh', display: 'flex',
            alignItems: 'center', justifyContent: 'center',
            background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        }}>
            <div style={{
                background: 'white', borderRadius: '16px',
                boxShadow: '0 20px 60px rgba(0,0,0,0.3)',
                padding: '40px 60px', maxWidth: '400px', width: '100%', textAlign: 'center',
            }}>
                <h1 style={{ color: '#667eea', marginBottom: '10px', fontSize: '2rem', fontWeight: 'bold' }}>
                    Đăng nhập
                </h1>
                {error && (
                    <p style={{ color: 'red', marginBottom: '16px', fontSize: '0.9rem' }}>{error}</p>
                )}
                <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column' }}>
                    <input
                        name="username"
                        type="text"
                        placeholder="Tên đăng nhập"
                        required
                        style={{
                            padding: '12px 20px', marginBottom: '20px',
                            borderRadius: '8px', border: '1px solid #ccc', fontSize: '1rem',
                        }}
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="Mật khẩu"
                        required
                        style={{
                            padding: '12px 20px', marginBottom: '30px',
                            borderRadius: '8px', border: '1px solid #ccc', fontSize: '1rem',
                        }}
                    />
                    <button type="submit" style={{
                        padding: '12px 20px', background: '#667eea', color: 'white',
                        border: 'none', borderRadius: '8px', fontSize: '1rem', cursor: 'pointer',
                    }}>
                        Đăng nhập
                    </button>
                </form>
            </div>
        </div>
    );
}