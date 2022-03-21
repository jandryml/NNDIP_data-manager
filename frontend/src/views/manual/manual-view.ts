import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import './manual-form';

@customElement('manual-view')
export class ManualView extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-vertical-layout style="padding: var(--lumo-space-m); width: 100%; height: 100%;" theme="spacing">
 <vaadin-horizontal-layout theme="spacing">
  <vaadin-text-field placeholder="Filter by name ..." type="text" tabindex=""></vaadin-text-field>
  <vaadin-button>
    Add 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; height: 100%;">
  <vaadin-grid style="width: 100%; height: 100%; flex: 1;" tabindex="" is-attached></vaadin-grid>
  <manual-form style="flex:1 ;"></manual-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
