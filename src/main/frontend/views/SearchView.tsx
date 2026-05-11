import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {useEffect, useState} from 'react';
import { useSearchParams } from 'react-router';
import { findByQuery } from 'Frontend/generated/SearchEndpoint';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/entity/EntityX";
import {HnomQngu} from "Frontend/utils/entityUtils";
import {SearchBar} from "Frontend/views/SearchBar";

export const config: ViewConfig = {
  menu: { order: 1, icon: 'la la-search' },
  title: 'Tìm Chữ',
  route: 'search',
};

export default function SearchView() {
  const [searchParams] = useSearchParams();
  const [searchResults, setSearchResults] = useState<EntityX[]>([]);

  useEffect(() => {
    const query = searchParams.get('query');
    if (query) {
      findByQuery(query).then((results) => setSearchResults((results ?? []).filter((r): r is EntityX => r != null)));
    } else {
      setSearchResults([]);
    }
  }, [searchParams]);

  return (
    <div className="view-container">
      <div className="search-section">
        <SearchBar />
      </div>

      {/* Content Area */}
      <div className="content-container">
        {searchParams.get('query') ? (
          <div>
            <h2>
              Kết quả tìm kiếm cho: "{searchParams.get('query')}"
            </h2>
            {searchResults.length > 0 ? (
              <div>
                {searchResults.map((result, index) => <ResultContent result={result} index={index} key={index}/>)}
              </div>
            ) : (
              <p>
                Không tìm thấy kết quả nào.
              </p>
            )}
          </div>
        ) : (
          <div style={{ textAlign: 'center', paddingTop: 'var(--lumo-space-l)' }}>
            <h2>
              Tra Cứu Chữ Nôm
            </h2>
            <p>
              Nhập ký tự chữ Nôm vào ô tìm kiếm để bắt đầu tra cứu.
            </p>
          </div>
        )}
      </div>
    </div>
  );
}


const ResultContent = ({ result, index }: { result: EntityX, index: number }) => {
    return (
        <div
            key={index}
            style={{
                padding: 'var(--lumo-space-l) var(--lumo-space-m)',
                borderBottom: '1px solid var(--lumo-contrast-10pct)',
                background: 'var(--lumo-base-color)',
                borderRadius: index === 0 ? 'var(--lumo-border-radius-m) var(--lumo-border-radius-m) 0 0' : '0',
                transition: 'background-color 0.2s',
            }}
            onMouseEnter={(e) => e.currentTarget.style.backgroundColor = 'var(--lumo-contrast-5pct)'}
            onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'var(--lumo-base-color)'}
        >
            <div style={{ marginBottom: 'var(--lumo-space-s)' }}>
                        <span style={{
                            fontSize: 'var(--lumo-font-size-xxl)',
                            display: 'inline-flex',
                            alignItems: 'center',
                            gap: 'var(--lumo-space-m)'
                        }}>
                            <span style={{
                                backgroundColor: 'var(--lumo-contrast-10pct)',
                                color: 'var(--lumo-body-text-color)',
                                padding: 'var(--lumo-space-xs) var(--lumo-space-s)',
                                borderRadius: 'var(--lumo-border-radius-s)',
                                fontSize: 'var(--lumo-font-size-s)',
                                fontWeight: '500',
                            }}>
                                {result.language?.abbreviation ?? 'N/A'}
                            </span>
                            <span style={{ color: 'var(--lumo-primary-text-color)', fontWeight: 'bold' }}>
                                <HnomQngu entityId={result.id} markedId={0}/>
                            </span>
                        </span>
            </div>
            <div style={{ color: 'var(--lumo-body-text-color)' }}>
                <span>{result.explanationsString}</span>
            </div>
        </div>
    );
};
