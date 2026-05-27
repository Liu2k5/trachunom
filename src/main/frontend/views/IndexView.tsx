import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useNavigate } from 'react-router';
import { Button } from '@vaadin/react-components';
import { SearchBar } from 'Frontend/views/SearchBar';
import {ImagePosUtil} from "Frontend/utils/imageUtils";

export const config: ViewConfig = {
  menu: { order: 0, icon: 'la la-home' },
  title: 'Tra Chữ Nôm',
  route: '',
};

export default function IndexView() {
  const navigate = useNavigate();

  return (
    <div className="view-container">
      <SearchBar />

      <div className="content-container">
          <div
            style={{
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              gap: 'var(--lumo-space-l)',
            }}
          >
            <div style={{ textAlign: 'center', marginBottom: 'var(--lumo-space-l)' }}>
              <h1>Tra Cứu Chữ Nôm</h1>
              <p
                style={{
                  color: 'var(--lumo-body-text-color)',
                  fontSize: '1.1rem',
                  lineHeight: '1.6',
                  maxWidth: '600px',
                  margin: '0 auto',
                }}
              >
                Công cụ tra cứu và tìm hiểu di sản chữ Nôm của Việt Nam
              </p>
            </div>

            <div
              style={{
                width: '100%',
                lineHeight: '1.8',
                textAlign: 'left',
                background: 'var(--lumo-base-color)',
                padding: 'var(--lumo-space-xl)',
                borderRadius: 'var(--lumo-border-radius-l)',
                border: '1px solid var(--lumo-contrast-10pct)',
                boxSizing: 'border-box'
              }}
            >
              <section style={{ marginBottom: 'var(--lumo-space-l)' }}>
                <h2 style={{ fontSize: '1.25rem', borderBottom: '1px solid var(--lumo-contrast-10pct)', paddingBottom: 'var(--lumo-space-s)' }}>Về Dự Án</h2>
                <p>
                  Chữ Nôm là hệ thống chữ viết truyền thống của người Việt, được sử
                  dụng để ghi lại tiếng Việt từ thế kỷ 13 đến đầu thế kỷ 20. Dự án
                  này nhằm xây dựng một cơ sở dữ liệu toàn diện về chữ Nôm, giúp
                  bảo tồn và phổ biến di sản văn hóa quý báu này.
                </p>
              </section>

              <section style={{ marginBottom: 'var(--lumo-space-l)' }}>
                <h2 style={{ fontSize: '1.25rem', borderBottom: '1px solid var(--lumo-contrast-10pct)', paddingBottom: 'var(--lumo-space-s)' }}>Tính Năng</h2>
                <ul
                  style={{
                    paddingLeft: 'var(--lumo-space-l)',
                    listStylePosition: 'inside',
                    color: 'var(--lumo-body-text-color)',
                  }}
                >
                  <li style={{ marginBottom: 'var(--lumo-space-s)' }}>
                    Tra cứu ký tự chữ Nôm nhanh chóng và chính xác
                  </li>
                  <li style={{ marginBottom: 'var(--lumo-space-s)' }}>
                    Quản lý từ điển với hệ thống phân loại chi tiết
                  </li>
                  <li style={{ marginBottom: 'var(--lumo-space-s)' }}>
                    Thông tin về cấu tạo, phát âm và ý nghĩa của từng ký tự
                  </li>
                </ul>
              </section>

              <section style={{ marginBottom: '0' }}>
                <h2 style={{ fontSize: '1.25rem', borderBottom: '1px solid var(--lumo-contrast-10pct)', paddingBottom: 'var(--lumo-space-s)' }}>Công Nghệ</h2>
                <ul
                  style={{
                    paddingLeft: 'var(--lumo-space-l)',
                    listStylePosition: 'inside',
                    color: 'var(--lumo-body-text-color)',
                  }}
                >
                  <li style={{ marginBottom: 'var(--lumo-space-xs)' }}>
                    <strong>Backend:</strong> Java Spring Boot
                  </li>
                  <li style={{ marginBottom: 'var(--lumo-space-xs)' }}>
                    <strong>Frontend:</strong> Vaadin Hilla (React + TypeScript)
                  </li>
                  <li style={{ marginBottom: 'var(--lumo-space-xs)' }}>
                    <strong>Database:</strong> PostgreSQL
                  </li>
                </ul>
              </section>

                <section style={{ marginBottom: 'var(--lumo-space-l)' }}>
                    <h2 style={{ fontSize: '1.25rem', borderBottom: '1px solid var(--lumo-contrast-10pct)', paddingBottom: 'var(--lumo-space-s)' }}>Nguồn dữ liệu</h2>
                    <p>
                        Xin gửi lời cảm ơn trân trọng tới Hội Bảo tồn Di sản Nôm vì những dữ liệu quý giá đã cung cấp, giúp dự án có thể phát triển và hoàn thiện hơn.
                        Dữ liệu này là nền tảng quan trọng để xây dựng cơ sở dữ liệu chữ Nôm toàn diện và chính xác.
                        Ngoài ra còn có sự đóng góp của các tài liệu được Thư viện Quốc gia công khai, từ điển tiếng Việt, cũng như từ các nhà nghiên cứu Hán Nôm.
                    </p>
                </section>
            </div>
          </div>

          <div
            style={{
              display: 'flex',
              gap: 'var(--lumo-space-m)',
              paddingTop: 'var(--lumo-space-l)',
              justifyContent: 'center'
            }}
          >
            <Button
              theme="primary"
              onClick={() => navigate('/admin/dictionary-management')}
            >
              Quản lý từ điển
            </Button>
          </div>
          <div>
              <ImagePosUtil imgLink={"https://lib.nomfoundation.org/site_media/nom/nlvnpf-0026/large/nlvnpf-0026-001.jpg"} />
          </div>
      </div>
    </div>
  );
}
