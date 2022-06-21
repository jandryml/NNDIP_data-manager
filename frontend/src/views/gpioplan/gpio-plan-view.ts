import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/grid/src/vaadin-grid.js';
import './gpio-plan-form';

@customElement('gpio-plan-view')
export class GpioPlanView extends LitElement {
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
  <vaadin-text-field placeholder="Filter by name ..." type="text" tabindex="" id="filterText"></vaadin-text-field>
  <vaadin-button id="addPlanButton">
    Add 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="width: 100%; height: 100%;">
  <vaadin-grid style=" height: 100%;" tabindex="" is-attached id="grid"></vaadin-grid>
  <gpio-plan-form id="gpioPlanForm" style="width: 100%; height: 100%;"></gpio-plan-form>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
