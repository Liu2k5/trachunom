import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useNavigate } from 'react-router';

export const config: ViewConfig = {
  menu: { order: 0, icon: 'la la-home' },
  title: 'Tra Chữ Nôm',
  route: '',
};

export default function IndexView() {
  const navigate = useNavigate();

  return (
    <div
      style={{
        width: '100%',
        height: '100vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
      }}
    >
      <div
        style={{
          background: 'white',
          borderRadius: '16px',
          boxShadow: '0 20px 60px rgba(0,0,0,0.3)',
          padding: '40px 60px',
          maxWidth: '600px',
          textAlign: 'center',
        }}
      >
        <h1
          style={{
            color: '#667eea',
            marginBottom: '10px',
            fontSize: '2.5rem',
            fontWeight: 'bold',
          }}
        >
          Tra Cứu Chữ Nôm
        </h1>

        <p
          style={{
            color: '#666',
            marginBottom: '30px',
            fontSize: '1.1rem',
            lineHeight: '1.6',
          }}
        >
          Xin chào đến với trang web Tra Chữ Nôm. Đây là nơi bạn có thể tra cứu
          các ký tự chữ Nôm một cách dễ dàng và nhanh chóng.
        </p>

        <MyButton text={'Quản lý từ điển'} link={'/admin/dictionary-management'}/>
        <MyButton text={'Tìm kiếm chữ Nôm'} link={'/search'}/>
        <MyButton text={' Giới thiệu dự án'} link={'/about'}/>
      </div>
    </div>
  );
  
    function MyButton({text, link}: {text: string, link: string}) {
        return (
            <button
                onClick={() => {
                    navigate(link);
                }}
                style={{
                    marginTop: '20px',
                    display: 'inline-block',
                    color: '#667eea',
                    textDecoration: 'none',
                    fontWeight: '500',
                    fontSize: '1rem',
                    padding: '10px 20px',
                    border: '2px solid #667eea',
                    borderRadius: '8px',
                    transition: 'all 0.3s ease',
                    background: 'transparent',
                    cursor: 'pointer',
                }}
                onMouseEnter={(e: React.MouseEvent<HTMLButtonElement>) => {
                    e.currentTarget.style.background = '#667eea';
                    e.currentTarget.style.color = 'white';
                }}
                onMouseLeave={(e: React.MouseEvent<HTMLButtonElement>) => {
                    e.currentTarget.style.background = 'transparent';
                    e.currentTarget.style.color = '#667eea';
                }}
            >
                {text}
            </button>
        );
    }
}

