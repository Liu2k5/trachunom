import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useNavigate } from 'react-router';

export const config: ViewConfig = {
  menu: { order: 3, icon: 'la la-info-circle' },
  title: 'Giới Thiệu',
  route: 'about',
};

export default function AboutView() {
  const navigate = useNavigate();

  return (
    <div
      style={{
        width: '100%',
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        padding: '40px 20px',
      }}
    >
      <div
        style={{
          maxWidth: '800px',
          margin: '0 auto',
          background: 'white',
          borderRadius: '16px',
          boxShadow: '0 20px 60px rgba(0,0,0,0.3)',
          padding: '40px',
        }}
      >
        <h1
          style={{
            color: '#667eea',
            fontSize: '2.5rem',
            marginBottom: '20px',
            textAlign: 'center',
          }}
        >
          Giới Thiệu
        </h1>

        <div style={{ color: '#555', fontSize: '16px', lineHeight: '1.8' }}>
          <section style={{ marginBottom: '30px' }}>
            <h2
              style={{
                color: '#667eea',
                fontSize: '1.5rem',
                marginBottom: '15px',
              }}
            >
              Về Dự Án
            </h2>
            <p>
              Chữ Nôm là hệ thống chữ viết truyền thống của người Việt, được sử
              dụng để ghi lại tiếng Việt từ thế kỷ 13 đến đầu thế kỷ 20. Dự án
              này nhằm xây dựng một cơ sở dữ liệu toàn diện về chữ Nôm, giúp
              bảo tồn và phổ biến di sản văn hóa quý báu này.
            </p>
          </section>

          <section style={{ marginBottom: '30px' }}>
            <h2
              style={{
                color: '#667eea',
                fontSize: '1.5rem',
                marginBottom: '15px',
              }}
            >
              Tính Năng
            </h2>
            <ul style={{ paddingLeft: '20px' }}>
              <li style={{ marginBottom: '10px' }}>
                🔍 Tra cứu ký tự chữ Nôm nhanh chóng và chính xác
              </li>
              <li style={{ marginBottom: '10px' }}>
                📚 Quản lý từ điển với hệ thống phân loại chi tiết
              </li>
              <li style={{ marginBottom: '10px' }}>
                🎯 Thông tin về cấu tạo, phát âm và ý nghĩa của từng ký tự
              </li>
              <li style={{ marginBottom: '10px' }}>
                📖 Ví dụ minh họa cách sử dụng trong ngữ cảnh thực tế
              </li>
              <li style={{ marginBottom: '10px' }}>
                🖼️ Hình ảnh minh họa các phong cách thư pháp khác nhau
              </li>
            </ul>
          </section>

          <section style={{ marginBottom: '30px' }}>
            <h2
              style={{
                color: '#667eea',
                fontSize: '1.5rem',
                marginBottom: '15px',
              }}
            >
              Công Nghệ
            </h2>
            <p>
              Dự án được xây dựng bằng công nghệ hiện đại:
            </p>
            <ul style={{ paddingLeft: '20px', marginTop: '10px' }}>
              <li style={{ marginBottom: '8px' }}>
                <strong>Backend:</strong> Java Spring Boot
              </li>
              <li style={{ marginBottom: '8px' }}>
                <strong>Frontend:</strong> Vaadin Hilla (React + TypeScript)
              </li>
              <li style={{ marginBottom: '8px' }}>
                <strong>Database:</strong> PostgreSQL
              </li>
            </ul>
          </section>

          <div style={{ textAlign: 'center', marginTop: '40px' }}>
            <button
              onClick={() => navigate('/search')}
              style={{
                padding: '12px 30px',
                fontSize: '16px',
                background: '#667eea',
                color: 'white',
                border: 'none',
                borderRadius: '8px',
                cursor: 'pointer',
                fontWeight: '500',
                marginRight: '10px',
              }}
              onMouseEnter={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = '#5568d3';
              }}
              onMouseLeave={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = '#667eea';
              }}
            >
              Bắt đầu tra cứu
            </button>
            <button
              onClick={() => navigate('/')}
              style={{
                padding: '12px 30px',
                fontSize: '16px',
                background: 'white',
                color: '#667eea',
                border: '2px solid #667eea',
                borderRadius: '8px',
                cursor: 'pointer',
                fontWeight: '500',
              }}
              onMouseEnter={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = '#f0f0f0';
              }}
              onMouseLeave={(e: React.MouseEvent<HTMLButtonElement>) => {
                e.currentTarget.style.background = 'white';
              }}
            >
              Trang chủ
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

