import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {useEffect, useState} from 'react';
import { useSearchParams } from 'react-router';
import { findByQuery } from 'Frontend/generated/SearchEndpoint';
import EntityX from "Frontend/generated/com/liu/trachunom/entity/entity/EntityX";
import {HnomQnguComponent} from "Frontend/utils/entityUtils";
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
    <div
      style={{
        width: 'auto',
        minHeight: '100vh',
        background: '#f5f5f5',
        padding: '20px',
      fontFamily: 'sans-serif',
      }}
    >
        <SearchBar/>

      {/* Content Area */}
      <div
        style={{
          maxWidth: '1000px',
          margin: '0 auto',
          background: 'white',
          borderRadius: '8px',
          boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
          padding: '30px',
          minHeight: '400px',
        }}
      >
        {searchParams.get('query') ? (
          <div>
            <h2 style={{ color: '#333', marginBottom: '20px' }}>
              Kết quả tìm kiếm cho: "{searchParams.get('query')}"
            </h2>
            {searchResults.length > 0 ? (
              <div>
                {/* Display search results here */}
                {searchResults.map((result, index) => <ResultContent result={result} index={index} key={index}/>)}
              </div>
            ) : (
              <p style={{ color: '#666', fontSize: '16px' }}>
                Không tìm thấy kết quả nào.
              </p>
            )}
          </div>
        ) : (
          <div style={{ textAlign: 'center', padding: '40px 0' }}>
            <h2 style={{ color: '#667eea', marginBottom: '10px' }}>
              Tra Cứu Chữ Nôm
            </h2>
            <p style={{ color: '#666', fontSize: '16px' }}>
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
                padding: '15px',
                borderBottom: '1px solid #eee',
                textDecoration: 'none',
                color: '#333',
            }}
            // href={"/entity/" + result.id}
        >
            <div>
                        <span style={{
                            fontSize: '30px',
                            display: 'inline-flex',
                            alignItems: 'start',
                        }}>
                            <span style={{
                                color: 'white',
                                backgroundColor: 'red',
                                padding: '2px 6px',
                                borderRadius: '4px',
                                marginRight: '10px',
                                fontSize: '20px',
                                fontWeight: 'bold',
                            }}>
                                {result.language?.abbreviation ?? 'N/A'}
                            </span>
                            <span>
                                <HnomQnguComponent entityId={result.id} markedId={0}/>
                            </span>
                        </span>
            </div>
            <div>
                <span>{result.explanationsString}</span>
            </div>
        </div>
    );
};
