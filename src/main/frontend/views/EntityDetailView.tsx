import {ViewConfig} from '@vaadin/hilla-file-router/types.js';
import {useParams, useNavigate} from 'react-router';
import {useEffect, useState} from 'react';
import type EntityDetailDto from 'Frontend/generated/com/liu/trachunom/dto/EntityDetailDto';
import {getEntityDetail} from 'Frontend/generated/EntityDetailEndpoint';
import EntityEvolutionDto from "Frontend/generated/com/liu/trachunom/dto/EntityEvolutionDto";
import {
    DrawEntityYear,
    DrawEvolution,
    DrawMeaningEvolution,
    DrawPronunciationEvolution,
    AnalyseStructure,
    HnomQngu,
} from 'Frontend/utils/entityUtils';
import {SearchBar} from "Frontend/views/SearchBar";

export const config: ViewConfig = {
    title: 'Chi Tiết Thực Thể',
    route: 'entity/:id',
};


export default function EntityDetailView() {
    const {id} = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [entity, setEntity] = useState<EntityDetailDto | null | undefined>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // TODO: Fetch entity data from backend
        // Example: EntityService.getById(id)
        // Mock data for demonstration
        getEntityDetail(Number(id)).then((data) => {
            setEntity(data);
            setLoading(false);
        }).catch((error) => {
            console.error('Error fetching entity:', error);
            setLoading(false);
        });
    }, [id]);

    if (loading) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div style={{fontSize: '18px', color: '#666'}}>Đang tải...</div>
            </div>
        );
    }

    if (!entity) {
        return (
            <div
                style={{
                    width: '100%',
                    minHeight: '100vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    background: '#f5f5f5',
                }}
            >
                <div>
                    <SearchBar/>
                    <div style={{textAlign: 'center'}}>
                        <h2 style={{color: '#666'}}>Không tìm thấy thực thể</h2>
                        <button
                            onClick={() => navigate('/search')}
                            style={{
                                marginTop: '20px',
                                padding: '10px 20px',
                                background: '#667eea',
                                color: 'white',
                                border: 'none',
                                borderRadius: '4px',
                                cursor: 'pointer',
                            }}
                        >
                            Quay lại tìm kiếm
                        </button>
                    </div>
                </div>
            </div>
        );
    }

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
            <div
                style={{
                    maxWidth: '1000px',
                    margin: '0 auto',
                }}
            >
                {/* Back button */}
                <button
                    onClick={() => navigate(-1)}
                    style={{
                        marginBottom: '20px',
                        padding: '8px 16px',
                        background: 'white',
                        border: '1px solid #ddd',
                        borderRadius: '4px',
                        cursor: 'pointer',
                        fontSize: '14px',
                    }}
                >
                    ← Quay lại
                </button>
                <SearchBar/>

                {/* Main content */}
                <div
                    style={{
                        background: 'white',
                        borderRadius: '8px',
                        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
                        padding: '40px',
                    }}
                >
                    <div
                        style={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            borderBottom: '2px solid #f0f0f0',
                            height: 'fit-content',
                        }}
                    >
                        {/* Character display */}
                        <div
                            style={{
                                textAlign: 'center',
                                marginBottom: '40px',
                                paddingBottom: '30px',
                                minWidth: '200px',
                                flex: '1'
                            }}
                        >
                            {!entity.compound ? (
                                    <>
                                        <div
                                            style={{
                                                fontSize: '80px',
                                                fontWeight: 'bold',
                                                color: '#667eea',
                                                margin: '0.5em 0px 0px',
                                                fontFamily: 'sans-serif',
                                                display: 'flex',
                                                justifyContent: 'center',
                                                height: '80px',
                                                position: 'relative',
                                            }}
                                        >
                                            <HnomQngu entityId={entity.id} markedId={0}/>
                                        </div>
                                        {entity.structure?.character ? (
                                            <div>
                                                <p>Unicode: U+{(entity.structure?.character?.characterString?.charCodeAt(0))?.toString(16).toUpperCase()}</p>
                                                <p>Bộ {entity.structure?.character?.radicalString}
                                                    + {entity.structure?.character?.additionalStrokeNumber} nét,
                                                    tổng {entity.structure?.character?.totalStrokeNumber}
                                                </p>
                                            </div>
                                        ) : (
                                                <div>
                                                    Kí tự này chưa được Unicode mã hóa
                                                </div>
                                            )
                                        }
                                        {/* Pronunciation */}
                                        {entity.pronunciation && (
                                            <section style={{marginBottom: '30px'}}>
                                                <div style={{paddingLeft: '16px'}}>
                                                    <div style={{fontSize: '18px', color: '#333'}}>
                                                        <DrawPronunciationEvolution pronunciationId={entity.pronunciation.id}/>
                                                    </div>
                                                </div>
                                            </section>
                                        )}
                                    </>
                                )
                                :
                                <>
                                    <div
                                        style={{
                                            fontSize: '80px',
                                            fontWeight: 'bold',
                                            color: '#667eea',
                                            margin: '0em 0px 0px',
                                            fontFamily: 'sans-serif',
                                            display: 'flex',
                                            justifyContent: 'center',
                                            // gap: '20px',
                                        }}
                                    >
                                        {entity.compositionComponents?.map((component, index) => (
                                            <div key={index}>
                                                {component ? (
                                                    <HnomQngu entityId={component.id} markedId={0}/>
                                                ) : (
                                                    <div style={{color: 'grey'}}>không có</div>
                                                )}
                                            </div>
                                        ))}
                                    </div>
                                    <div>
                                        <p>{entity.pronunciation?.pronunciationString}</p>
                                    </div>
                                </>
                            }
                        </div>

                        <div
                            style={{
                                minWidth: '200px',
                                flex: '1',
                            }}
                        >
                            {/* Structure */}
                            {entity.structure && (
                                <section style={{marginBottom: '30px'}}>
                                    <h2
                                        style={{
                                            color: '#667eea',
                                            fontSize: '20px',
                                            marginBottom: '15px',
                                            borderLeft: '4px solid #667eea',
                                            paddingLeft: '12px',
                                        }}
                                    >
                                        Cấu tạo
                                    </h2>
                                    <div style={{width: '100%',}}>
                                        <AnalyseStructure structure={entity.structure}/>
                                    </div>
                                </section>
                            )}
                            {/* Age */}
                            {

                                <section style={{marginBottom: '30px'}}>
                                    <h2
                                        style={{
                                            color: '#667eea',
                                            fontSize: '20px',
                                            marginBottom: '15px',
                                            borderLeft: '4px solid #667eea',
                                            paddingLeft: '12px',
                                        }}
                                    >
                                        Niên đại
                                    </h2>
                                    <div style={{paddingLeft: '16px'}}>
                                        <div style={{fontSize: '18px', color: '#333'}}>
                                            <DrawEntityYear entityId={entity.id} />
                                        </div>
                                    </div>
                                </section>
                            }
                            {/* Meanings */}
                            {entity.meaning?.explanations && entity.meaning.explanations.length > 0 && (
                                <section style={{marginBottom: '30px'}}>
                                    <h2
                                        style={{
                                            color: '#667eea',
                                            fontSize: '20px',
                                            marginBottom: '15px',
                                            borderLeft: '4px solid #667eea',
                                            paddingLeft: '12px',
                                        }}
                                    >
                                        Ý nghĩa
                                    </h2>
                                    <DrawMeaningEvolution meaning={entity.meaning}/>
                                </section>
                            )}
                            {/* Synonyms */}
                            {entity.synonyms && entity.synonyms.length > 0 && (
                                <section style={{marginBottom: '30px'}}>
                                    <h2
                                        style={{
                                            color: '#667eea',
                                            fontSize: '20px',
                                            marginBottom: '15px',
                                            borderLeft: '4px solid #667eea',
                                            paddingLeft: '12px',
                                        }}
                                    >
                                        Từ đồng nghĩa
                                    </h2>
                                    <div style={{paddingLeft: '16px', display: 'flex', gap: '10px'}}>
                                        {entity.synonyms?.map(synonym =>
                                            synonym ? (
                                                <div
                                                    key={synonym.id}
                                                    style={{
                                                        marginBottom: '10px',
                                                        fontSize: '30px',
                                                    }}
                                                >
                                                    <HnomQngu entityId={synonym.id} markedId={0}/>
                                                </div>
                                            ) : null
                                        )}
                                    </div>
                                </section>
                            )}
                        </div>
                    </div>

                    {/* Evolution */}
                    {entity.evolutions && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Quá trình phát triển
                            </h2>
                            <div style={{paddingLeft: '16px'}}>
                                <div>
                                    <HnomQngu entityId={entity.id} markedId={0}/>
                                    <p>{entity.explanationsString}</p>
                                </div>
                                <DrawEvolution evolutions={entity.evolutions?.filter((evolution): evolution is EntityEvolutionDto => evolution !== undefined)} />
                            </div>
                        </section>
                    )}

                    {/* Variances */}
                    {entity.variances && entity.variances.length > 0 && (
                        <section style={{marginBottom: '30px'}}>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Biến thể
                            </h2>
                            <div style={{paddingLeft: '16px', display: 'flex', gap: '10px'}}>
                                {entity.variances.map(variance =>
                                    variance ? (
                                        <div
                                            key={variance.id}
                                            style={{
                                                marginBottom: '10px',
                                                fontSize: '30px',
                                            }}
                                        >
                                            <HnomQngu entityId={variance.id} markedId={0}/>
                                        </div>
                                    ) : null
                                )}
                            </div>
                        </section>
                    )}

                    {/* Being semantic component */}
                    {entity.beingSemanticComponents && entity.beingSemanticComponents.length > 0 && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Làm hình bàng
                            </h2>
                            <div style={{paddingLeft: '16px', display: 'flex', gap: '10px'}}>
                                {entity.beingSemanticComponents?.map(fetchedEntity => (
                                    <div
                                        key={fetchedEntity?.id}
                                        style={{
                                            marginBottom: '10px',
                                            fontSize: '30px',
                                        }}
                                    >
                                        <HnomQngu entityId={fetchedEntity?.id} markedId={0}/>
                                    </div>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Being phonetic component */}
                    {entity.beingPhoneticComponents && entity.beingPhoneticComponents.length > 0 && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Làm thanh bàng
                            </h2>
                            <div style={{paddingLeft: '16px', display: 'flex', gap: '10px'}}>
                                {entity.beingPhoneticComponents?.map(fetchedEntity => (
                                    <div
                                        key={fetchedEntity?.id}
                                        style={{
                                            marginBottom: '10px',
                                            fontSize: '30px',
                                        }}
                                    >
                                        <HnomQngu entityId={fetchedEntity?.id} markedId={0}/>
                                    </div>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Having same semantic component */}
                    {entity.havingSameSemanticComponents && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Cùng hình bàng
                            </h2>
                            <div style={{paddingLeft: '16px', gap: '10px'}}>
                                {Object.entries(entity.havingSameSemanticComponents)?.map(([key, fetchedEntity]) => (
                                    <>
                                        <div
                                            style={{
                                                display: 'flex',
                                                wrap: 'wrap',
                                                alignItems: 'start',
                                            }}
                                        >
                                            <h1
                                                style={{
                                                    margin: '0px',
                                                    lineHeight: '1',
                                                    color: '#667eea',
                                                    fontSize: '20px',
                                                    marginRight: '20px',
                                                }}
                                            >{key}</h1>
                                            <div
                                                key={key}
                                                style={{
                                                    margin: '0px',
                                                    fontSize: '20px',
                                                    display: 'flex',
                                                    gap: '10px',
                                                    flexWrap: 'wrap',
                                                }}
                                            >
                                                {fetchedEntity?.map(entity => (
                                                    <HnomQngu entityId={entity?.id} markedId={0}/>
                                                ))}
                                            </div>
                                        </div>
                                    </>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Having same phonetic component */}
                    {entity.havingSamePhoneticComponents && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Cùng thanh bàng
                            </h2>
                            <div style={{paddingLeft: '16px', gap: '10px'}}>
                                {Object.entries(entity.havingSamePhoneticComponents)?.map(([key, fetchedEntity]) => (
                                    <>
                                        <div
                                            style={{
                                                display: 'flex',
                                                wrap: 'wrap',
                                                alignItems: 'start',
                                                marginBottom: '1em',
                                            }}
                                        >
                                            <h1
                                                style={{
                                                    margin: '0px',
                                                    lineHeight: '1',
                                                    color: '#667eea',
                                                    fontSize: '20px',
                                                    marginRight: '20px',
                                                }}
                                            >{key}</h1>
                                            <div
                                                key={key}
                                                style={{
                                                    margin: '0px',
                                                    fontSize: '20px',
                                                    display: 'flex',
                                                    gap: '10px',
                                                    flexWrap: 'wrap',
                                                }}
                                            >
                                                {fetchedEntity?.map(entity => (
                                                    <HnomQngu entityId={entity?.id} markedId={0}/>
                                                ))}
                                            </div>
                                        </div>
                                    </>
                                ))}
                            </div>
                        </section>
                    )}

                    {/* Examples */}
                    {entity.examples && entity.examples.length > 0 && (
                        <section>
                            <h2
                                style={{
                                    color: '#667eea',
                                    fontSize: '20px',
                                    marginBottom: '15px',
                                    borderLeft: '4px solid #667eea',
                                    paddingLeft: '12px',
                                }}
                            >
                                Ví dụ
                            </h2>
                            {entity?.examples ? (
                                    entity.examples.map((example, index) =>
                                        <div
                                            key={index}
                                            style={{
                                                display: 'flex',
                                                flexWrap: 'wrap',
                                                gap: '20px',
                                                marginBottom: '0.5em',
                                                fontFamily: 'sans-serif',
                                                fontSize: '30px'
                                            }}>
                                            {
                                                example?.exampleWords ? (
                                                    example.exampleWords.map((word, index) => {
                                                            if (word?.entity) {
                                                                return (
                                                                    <HnomQngu entityId={word.entity.id} markedId={entity.id ?? 0} key={index}/>
                                                                );
                                                            }
                                                            return undefined;
                                                        }
                                                    )
                                                ) : <div>khong co example</div>
                                            }
                                            <p
                                                title={example ? example.sourceDescription : ''}
                                                style={{
                                                    fontFamily: 'sans-serif',
                                                    fontStyle: 'italic',
                                                    color: '#666',
                                                    fontSize: '0.5em',
                                                }}
                                            >
                                                {example ? example.sourceName : ''}
                                            </p>
                                        </div>
                                    )
                                )
                                : (
                                    <p style={{color: '#666', fontSize: '16px'}}>
                                        Không tìm thấy kết quả nào.
                                    </p>
                                )
                            }
                        </section>
                    )}
                </div>
            </div>
        </div>
    );
}
